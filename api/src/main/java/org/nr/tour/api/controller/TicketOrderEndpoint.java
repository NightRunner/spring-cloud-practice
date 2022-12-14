package org.nr.tour.api.controller;

import org.nr.tour.api.Constants;
import org.nr.tour.api.service.OrderPayNotifyService;
import org.nr.tour.api.service.OrderService;
import org.nr.tour.api.service.PayService;
import org.nr.tour.api.service.VoBuilderService;
import org.nr.tour.api.support.APIResult;
import org.nr.tour.api.support.ErrorCode;
import org.nr.tour.api.support.ServiceException;
import org.nr.tour.api.vo.*;
import org.nr.tour.common.util.DateUtils;
import org.nr.tour.common.util.JsonService;
import org.nr.tour.domain.*;
import org.nr.tour.rpc.hystrix.*;
import org.nr.tour.util.MemberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@RestController
public class TicketOrderEndpoint {

    private static final String UTF_8_META = "<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\" />";

    @Autowired
    HystrixMemberServiceClient hystrixMemberServiceClient;

    @Autowired
    HystrixTicketOrderServiceClient hystrixTicketOrderServiceClient;

    @Autowired
    VoBuilderService voBuilderService;

    @Autowired
    HystrixInsuranceServiceClient hystrixInsuranceServiceClient;

    @Autowired
    HystrixVerifyCodeServiceClient hystrixVerifyCodeServiceClient;

    @Autowired
    PayService payService;

    @Autowired
    HystrixTicketServiceClient hystrixTicketServiceClient;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderPayNotifyService orderPayNotifyService;

    @Autowired
    private JsonService jsonService;

    @RequestMapping("ticket/order/start")
    public Object ticketOrderStart(@RequestParam String ticketId,
                                   @RequestParam(required = false, defaultValue = "") String token) {

        String realToken = token;
        Member member = null;

        if (StringUtils.isEmpty(token)) {
            Member guestMember = hystrixMemberServiceClient.createGuest();
            realToken = guestMember.getToken();
            member = guestMember;
        } else {
            member = getAndCheckMemberByToken(token);
        }

        TicketOrder ticketOrder = hystrixTicketOrderServiceClient.createByTicketIdAndMemberId(ticketId, member.getId());

        Map<String, String> map = new HashMap<String, String>();
        map.put("orderId", ticketOrder.getId());
        map.put("token", realToken);

        return APIResult.createSuccess(map);
    }

    /**
     * @param orderId
     * @param token
     * @return
     */
    @RequestMapping("ticket/order/info")
    public Object ticketOrderInfo(@RequestParam String orderId,
                                  @RequestParam String token) {

        getAndCheckMemberByToken(token);

        TicketOrder ticketOrder = getAndCheckByOrderId(orderId);

        getOrderAndCheckTokenCanOperateOrder(token, orderId);

        TicketOrderInfoVO vo = voBuilderService.buildVO(ticketOrder);

        return APIResult.createSuccess(vo);
    }

    private TicketOrder getAndCheckByOrderId(String orderId) {
        final TicketOrder ticketOrder = hystrixTicketOrderServiceClient.getById(orderId);
        if (ticketOrder == null) {
            throw new ServiceException("???????????????ID");
        }
        return ticketOrder;
    }

    private Member getAndCheckMemberByToken(String token) {
        final Member member = hystrixMemberServiceClient.getByToken(token);
        if (member == null) {
            throw new ServiceException("???????????????", ErrorCode.TOKEN_EXPIRED);
        }
        return member;
    }

    @RequestMapping("ticket/order/commit")
    public Object ticketOrderCommit(
            @RequestParam String orderId, @RequestParam String token,
            @RequestParam String playTime,
            @RequestParam String visitors,
            @RequestParam Integer childCount,
            @RequestParam Integer adultCount,
            @RequestParam String bookingPersonRealName, @RequestParam String bookingPersonMobilePhone,
            @RequestParam(required = false, defaultValue = "") String code) {

        Member member = getAndCheckMemberByToken(token);
        TicketOrder ticketOrder = getAndCheckByOrderId(orderId);

        getOrderAndCheckTokenCanOperateOrder(token, orderId);

        if ((adultCount + childCount) <= 0) {
            throw new ServiceException("?????????0,????????????");
        }

        if (MemberUtils.isGuestMember(member) && StringUtils.isEmpty(code)) {
            throw new ServiceException("??????????????????,????????????");
        }

        if (MemberUtils.isGuestMember(member) && isValidCode(bookingPersonMobilePhone, code)) {
            throw new ServiceException("??????????????????", ErrorCode.TOKEN_EXPIRED);
        }

        if (!orderService.canChangeStatus(OrderStatusEnum.getById(ticketOrder.getStatus()), OrderStatusEnum.UN_PAID)) {
            throw new ServiceException("?????????????????????,????????????");
        }

        ticketOrder.setPlayTime(getDate(playTime));
        ticketOrder.setChildCount(childCount);
        ticketOrder.setAdultCount(adultCount);
        ticketOrder.setBookingPersonMobilePhone(bookingPersonMobilePhone);
        ticketOrder.setBookingPersonRealName(bookingPersonRealName);

        String finalToken = token;

        //?????????????????????,?????????????????????????????????bookingPersonMobilePhone?????????,?????????????????????memberId??????????????????,??????????????????????????????????????????bookingPersonMobilePhone
        if (MemberUtils.isGuestMember(member)) {
            Member alreadyExistMember = hystrixMemberServiceClient.getByMobilePhone(bookingPersonMobilePhone);
            if (alreadyExistMember == null) {
                member.setMobilePhone(bookingPersonMobilePhone);
                hystrixMemberServiceClient.save(jsonService.toJson(member));
            } else {
                ticketOrder.setMemberId(alreadyExistMember.getId());
                finalToken = hystrixMemberServiceClient.login(alreadyExistMember.getMobilePhone());
            }
        }

        List<TicketOrderVisitor> lineOrderVisitorList = new ArrayList<TicketOrderVisitor>();
        List<TicketOrderVisitorVO> visitorVOList = jsonService.parseArray(visitors, TicketOrderVisitorVO.class);
        for (TicketOrderVisitorVO vo : visitorVOList) {
            final TicketOrderVisitor lineOrderVisitor = new TicketOrderVisitor();
            BeanUtils.copyProperties(vo, lineOrderVisitor);
            lineOrderVisitorList.add(lineOrderVisitor);
        }

        hystrixTicketOrderServiceClient.updateVisitors(orderId, jsonService.toJson(lineOrderVisitorList));

        orderService.changeStatus(ticketOrder, OrderStatusEnum.getById(ticketOrder.getStatus()), OrderStatusEnum.UN_PAID);

        OrderPriceVO orderPriceVO = getOrderPriceVO(token, orderId, adultCount, childCount);
        ticketOrder.setPrice(orderPriceVO.getPrice());
        hystrixTicketOrderServiceClient.save(jsonService.toJson(ticketOrder));

        Map<String, String> map = new HashMap<String, String>();
        map.put("orderId", orderId);
        map.put("token", finalToken);

        return APIResult.createSuccess(map);
    }

    private boolean isValidCode(String bookingPersonMobilePhone, String code) {
        return !hystrixVerifyCodeServiceClient.verify(bookingPersonMobilePhone, code, Constants.TYPE_VERIFY_CODE_ALL);
    }

    private Date getDate(String date) {
        Date result = DateUtils.parseDate(date);
        if (result == null) {
            throw new ServiceException("????????????", ErrorCode.COMMON);
        }
        return result;
    }

    @RequestMapping("ticket/order/cancel")
    public Object ticketOrderCancel(@RequestParam String token,
                                    @RequestParam String orderId,
                                    @RequestParam(required = false, defaultValue = "") String reason) {

        TicketOrder ticketOrder = getOrderAndCheckTokenCanOperateOrder(token, orderId);

        orderService.changeStatus(ticketOrder, OrderStatusEnum.getById(ticketOrder.getStatus()), OrderStatusEnum.CANCEL);
        ticketOrder.setCancelReason(reason);

        hystrixTicketOrderServiceClient.save(jsonService.toJson(ticketOrder));

        return APIResult.createSuccess(Boolean.TRUE);
    }

    @RequestMapping("ticket/order/calculatePrice")
    public Object ticketOrderCalculatePrice(@RequestParam String token,
                                            @RequestParam String orderId,
                                            @RequestParam Integer adultCount,
                                            @RequestParam Integer childCount) {
        return APIResult.createSuccess(getOrderPriceVO(token, orderId, adultCount, childCount));
    }

    private OrderPriceVO getOrderPriceVO(String token, String orderId, Integer adultCount, Integer childCount) {
        Double price = 0D;
        Double marketPrice = 0D;

        TicketOrder ticketOrder = getOrderAndCheckTokenCanOperateOrder(token, orderId);

        String ticketId = ticketOrder.getTicketId();

        Ticket ticket = hystrixTicketServiceClient.getById(ticketId);
        Double personPrice = ticket.getPrice();
        Double marketPersonPrice = ticket.getMarketPrice();

        price += personPrice * (adultCount + childCount);
        marketPrice += marketPersonPrice * (adultCount + childCount);

        OrderPriceVO orderPriceVO = new OrderPriceVO();
        orderPriceVO.setPrice(price);
        orderPriceVO.setMarketPrice(marketPrice);
        return orderPriceVO;
    }

    private TicketOrder getOrderAndCheckTokenCanOperateOrder(String token, String orderId) {
        TicketOrder ticketOrder = getAndCheckByOrderId(orderId);
        Member member = getAndCheckMemberByToken(token);
        String memberId = ticketOrder.getMemberId();
        if (!memberId.equals(member.getId())) {
            throw new ServiceException("??????????????????,????????????");
        }
        return ticketOrder;
    }

    @RequestMapping("ticket/order/detail")
    public Object ticketOrderDetail(@RequestParam String token, @RequestParam String orderId) {

        TicketOrder ticketOrder = getOrderAndCheckTokenCanOperateOrder(token, orderId);

        TicketOrderDetailVO vo = voBuilderService.buildOrderDetail(ticketOrder);

        return APIResult.createSuccess(vo);
    }

    @RequestMapping("ticket/order/pay")
    public Object ticketOrderPay(@RequestParam("orderId") String orderId, @RequestParam("token") String token) {

        TicketOrder ticketOrder = getOrderAndCheckTokenCanOperateOrder(token, orderId);

//        Double price = ticketOrder.getPrice();
        Double price = 0.1D;

        String content = payService.getPayString(ticketOrder.getSn(), price);

        return APIResult.createSuccess(UTF_8_META + content);
    }

    @RequestMapping("ticket/order/pay/success")
    public Object ticketOrderPaySuccess(@RequestParam("orderId") String orderId, @RequestParam("token") String token) {
        TicketOrder ticketOrder = getOrderAndCheckTokenCanOperateOrder(token, orderId);

        orderPayNotifyService.success(ticketOrder.getSn(), PayTypeEnum.QUICK);

        return APIResult.createSuccess();
    }

    @RequestMapping("ticket/order/list")
    public Object ticketOrderList(@RequestParam String token,
                                  @RequestParam(required = false, defaultValue = "" + OrderService.ORDER_STATUS_ALL) Integer status,
                                  @RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                  @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Member member = getAndCheckMemberByToken(token);

        PageImplWrapper<TicketOrder> pageImplWrapper = null;

        Integer[] realStates = orderService.getRealOrderStatues(status);
        pageImplWrapper = hystrixTicketOrderServiceClient.findPageByMemberIdAndStatues(
                member.getId(), realStates, pageNo, pageSize);

        List<TicketOrder> ticketOrders = pageImplWrapper.getContent();

        List<TicketOrderDetailVO> vos = new ArrayList<TicketOrderDetailVO>();

        for (TicketOrder ticketOrder : ticketOrders) {
            vos.add(voBuilderService.buildOrderDetail(ticketOrder));
        }

        return APIResult.createSuccess(new PageVO<TicketOrderDetailVO>(vos, !pageImplWrapper.isLast()));
    }

}
