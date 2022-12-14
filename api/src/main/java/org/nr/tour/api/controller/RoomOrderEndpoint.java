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
public class RoomOrderEndpoint {

    private static final String UTF_8_META = "<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\" />";

    @Autowired
    HystrixMemberServiceClient hystrixMemberServiceClient;

    @Autowired
    HystrixRoomOrderServiceClient hystrixRoomOrderServiceClient;

    @Autowired
    VoBuilderService voBuilderService;

    @Autowired
    HystrixInsuranceServiceClient hystrixInsuranceServiceClient;

    @Autowired
    HystrixVerifyCodeServiceClient hystrixVerifyCodeServiceClient;

    @Autowired
    PayService payService;

    @Autowired
    HystrixRoomServiceClient hystrixRoomServiceClient;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderPayNotifyService orderPayNotifyService;

    @Autowired
    private JsonService jsonService;

    @RequestMapping("room/order/start")
    public Object roomOrderStart(@RequestParam String roomId,
                                 @RequestParam String checkInTime,
                                 @RequestParam String leaveTime,
                                 @RequestParam(required = false, defaultValue = "") String token) {

        final Date checkInTimeDate = DateUtils.parseDate(checkInTime);
        final Date leaveTimeDate = DateUtils.parseDate(leaveTime);

        if (checkInTimeDate == null) {
            throw new ServiceException("?????????????????????");
        }

        if (leaveTimeDate == null) {
            throw new ServiceException("?????????????????????");
        }

        String realToken = token;
        Member member = null;

        if (StringUtils.isEmpty(token)) {
            Member guestMember = hystrixMemberServiceClient.createGuest();
            realToken = guestMember.getToken();
            member = guestMember;
        } else {
            member = getAndCheckMemberByToken(token);
        }

        Room room = hystrixRoomServiceClient.getById(roomId);

        if (room == null) {
            throw new ServiceException("??????????????????");
        }

        RoomOrder roomOrder = hystrixRoomOrderServiceClient.createByRoomIdAndMemberId(roomId, member.getId());

        roomOrder.setCheckInTime(checkInTimeDate);
        roomOrder.setLeaveTime(leaveTimeDate);

        hystrixRoomOrderServiceClient.save(jsonService.toJson(roomOrder));

        Map<String, String> map = new HashMap<String, String>();
        map.put("orderId", roomOrder.getId());
        map.put("token", realToken);

        return APIResult.createSuccess(map);
    }

    /**
     * @param orderId
     * @param token
     * @return
     */
    @RequestMapping("room/order/info")
    public Object roomOrderInfo(@RequestParam String orderId,
                                @RequestParam String token) {

        getAndCheckMemberByToken(token);

        RoomOrder roomOrder = getAndCheckByOrderId(orderId);

        getOrderAndCheckTokenCanOperateOrder(token, orderId);

        RoomOrderInfoVO vo = voBuilderService.buildVO(roomOrder);

        return APIResult.createSuccess(vo);
    }

    private RoomOrder getAndCheckByOrderId(String orderId) {
        final RoomOrder roomOrder = hystrixRoomOrderServiceClient.getById(orderId);
        if (roomOrder == null) {
            throw new ServiceException("???????????????ID");
        }
        return roomOrder;
    }

    private Member getAndCheckMemberByToken(String token) {
        final Member member = hystrixMemberServiceClient.getByToken(token);
        if (member == null) {
            throw new ServiceException("???????????????", ErrorCode.TOKEN_EXPIRED);
        }
        return member;
    }

    @RequestMapping("room/order/commit")
    public Object roomOrderCommit(
            @RequestParam String orderId, @RequestParam String token,
            @RequestParam Integer count,
            @RequestParam Integer keepTillTime,
            @RequestParam String guests,
            @RequestParam String bookingPersonRealName, @RequestParam String bookingPersonMobilePhone,
            @RequestParam(required = false, defaultValue = "") String code) {

        Member member = getAndCheckMemberByToken(token);
        RoomOrder roomOrder = getAndCheckByOrderId(orderId);

        getOrderAndCheckTokenCanOperateOrder(token, orderId);

        if (count <= 0) {
            throw new ServiceException("?????????0,????????????");
        }

        if (MemberUtils.isGuestMember(member) && StringUtils.isEmpty(code)) {
            throw new ServiceException("??????????????????,????????????");
        }

        if (MemberUtils.isGuestMember(member) && isValidCode(bookingPersonMobilePhone, code)) {
            throw new ServiceException("??????????????????", ErrorCode.TOKEN_EXPIRED);
        }

        if (!orderService.canChangeStatus(OrderStatusEnum.getById(roomOrder.getStatus()), OrderStatusEnum.UN_PAID)) {
            throw new ServiceException("?????????????????????,????????????");
        }

        roomOrder.setKeepTillTime(keepTillTime);
        roomOrder.setCount(count);
        roomOrder.setBookingPersonMobilePhone(bookingPersonMobilePhone);
        roomOrder.setBookingPersonRealName(bookingPersonRealName);

        String finalToken = token;

        //?????????????????????,?????????????????????????????????bookingPersonMobilePhone?????????,?????????????????????memberId??????????????????,??????????????????????????????????????????bookingPersonMobilePhone
        if (MemberUtils.isGuestMember(member)) {
            Member alreadyExistMember = hystrixMemberServiceClient.getByMobilePhone(bookingPersonMobilePhone);
            if (alreadyExistMember == null) {
                member.setMobilePhone(bookingPersonMobilePhone);
                hystrixMemberServiceClient.save(jsonService.toJson(member));
            } else {
                roomOrder.setMemberId(alreadyExistMember.getId());
                finalToken = hystrixMemberServiceClient.login(alreadyExistMember.getMobilePhone());
            }
        }

        orderService.changeStatus(roomOrder, OrderStatusEnum.getById(roomOrder.getStatus()), OrderStatusEnum.UN_PAID);

        OrderPriceVO orderPriceVO = getOrderPriceVO(token, orderId, count);
        roomOrder.setPrice(orderPriceVO.getPrice());
        hystrixRoomOrderServiceClient.save(jsonService.toJson(roomOrder));

        List<RoomOrderGuest> roomOrderGuests = new ArrayList<RoomOrderGuest>();
        List<RoomOrderGuestVO> roomOrderGuestVOs = jsonService.parseArray(guests, RoomOrderGuestVO.class);

        for (RoomOrderGuestVO vo : roomOrderGuestVOs) {
            final RoomOrderGuest roomOrderVisitor = new RoomOrderGuest();
            BeanUtils.copyProperties(vo, roomOrderVisitor);
            roomOrderGuests.add(roomOrderVisitor);
        }
        hystrixRoomOrderServiceClient.updateGuests(orderId, jsonService.toJson(roomOrderGuests));

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

    @RequestMapping("room/order/cancel")
    public Object roomOrderCancel(@RequestParam String token,
                                  @RequestParam String orderId,
                                  @RequestParam(required = false, defaultValue = "") String reason) {

        RoomOrder roomOrder = getOrderAndCheckTokenCanOperateOrder(token, orderId);

        orderService.changeStatus(roomOrder, OrderStatusEnum.getById(roomOrder.getStatus()), OrderStatusEnum.CANCEL);
        roomOrder.setCancelReason(reason);

        hystrixRoomOrderServiceClient.save(jsonService.toJson(roomOrder));

        return APIResult.createSuccess(Boolean.TRUE);
    }

    @RequestMapping("room/order/calculatePrice")
    public Object roomOrderCalculatePrice(@RequestParam String token,
                                          @RequestParam String orderId,
                                          @RequestParam Integer count) {
        return APIResult.createSuccess(getOrderPriceVO(token, orderId, count));
    }

    private OrderPriceVO getOrderPriceVO(String token, String orderId, Integer count) {
        Double price = 0D;
        Double marketPrice = 0D;

        RoomOrder roomOrder = getOrderAndCheckTokenCanOperateOrder(token, orderId);
        Integer days = DateUtils.getDistanceOfTwoDate(roomOrder.getLeaveTime(), roomOrder.getCheckInTime());

        String roomId = roomOrder.getRoomId();

        Room room = hystrixRoomServiceClient.getById(roomId);
        Double personPrice = room.getPrice();
        Double marketPersonPrice = room.getMarketPrice();

        price += personPrice * count * days;
        marketPrice += marketPersonPrice * count * days;

        OrderPriceVO orderPriceVO = new OrderPriceVO();
        orderPriceVO.setPrice(price);
        orderPriceVO.setMarketPrice(marketPrice);
        return orderPriceVO;
    }

    private RoomOrder getOrderAndCheckTokenCanOperateOrder(String token, String orderId) {
        RoomOrder roomOrder = getAndCheckByOrderId(orderId);
        Member member = getAndCheckMemberByToken(token);
        String memberId = roomOrder.getMemberId();
        if (!memberId.equals(member.getId())) {
            throw new ServiceException("??????????????????,????????????");
        }
        return roomOrder;
    }

    @RequestMapping("room/order/detail")
    public Object roomOrderDetail(@RequestParam String token, @RequestParam String orderId) {

        RoomOrder roomOrder = getOrderAndCheckTokenCanOperateOrder(token, orderId);

        RoomOrderDetailVO vo = voBuilderService.buildOrderDetail(roomOrder);

        return APIResult.createSuccess(vo);
    }

    @RequestMapping("room/order/pay")
    public Object roomOrderPay(@RequestParam("orderId") String orderId, @RequestParam("token") String token) {

        RoomOrder roomOrder = getOrderAndCheckTokenCanOperateOrder(token, orderId);

//        Double price = roomOrder.getPrice();
        Double price = 0.1D;

        String content = payService.getPayString(roomOrder.getSn(), price);

        return APIResult.createSuccess(UTF_8_META + content);
    }

    @RequestMapping("room/order/list")
    public Object roomOrderList(@RequestParam String token,
                                @RequestParam(required = false, defaultValue = "" + OrderService.ORDER_STATUS_ALL) Integer status,
                                @RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Member member = getAndCheckMemberByToken(token);

        PageImplWrapper<RoomOrder> pageImplWrapper = null;

        if (status == OrderService.ORDER_STATUS_ALL) {
            pageImplWrapper = hystrixRoomOrderServiceClient.findPageByMemberId(
                    member.getId(), pageNo, pageSize);
        } else {
            Integer[] realStates = orderService.getRealOrderStatues(status);
            pageImplWrapper = hystrixRoomOrderServiceClient.findPageByMemberIdAndStatues(
                    member.getId(), realStates, pageNo, pageSize);
        }

        List<RoomOrder> roomOrders = pageImplWrapper.getContent();

        List<RoomOrderDetailVO> vos = new ArrayList<RoomOrderDetailVO>();

        for (RoomOrder roomOrder : roomOrders) {
            vos.add(voBuilderService.buildOrderDetail(roomOrder));
        }

        return APIResult.createSuccess(new PageVO<RoomOrderDetailVO>(vos, !pageImplWrapper.isLast()));
    }
}
