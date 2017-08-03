package org.nr.tour.api.service.impl;

import org.nr.tour.api.service.OrderPayNotifyService;
import org.nr.tour.api.service.OrderService;
import org.nr.tour.common.util.JsonService;
import org.nr.tour.common.util.OrderSnUtils;
import org.nr.tour.domain.*;
import org.nr.tour.rpc.hystrix.HystrixLineOrderServiceClient;
import org.nr.tour.rpc.hystrix.HystrixRoomOrderServiceClient;
import org.nr.tour.rpc.hystrix.HystrixTicketOrderServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Service
public class OrderPayNotifyServiceImpl implements OrderPayNotifyService {

    @Autowired
    HystrixLineOrderServiceClient hystrixLineOrderServiceClient;

    @Autowired
    HystrixRoomOrderServiceClient hystrixRoomOrderServiceClient;

    @Autowired
    HystrixTicketOrderServiceClient hystrixTicketOrderServiceClient;

    @Autowired
    JsonService jsonService;

    @Autowired
    OrderService orderService;

    @Override
    public void success(String orderSn, PayTypeEnum payTypeEnum) {

        System.out.println("*********************");
        System.out.println(orderSn);
        System.out.println("*********************");

        if (OrderSnUtils.isLineOrderSn(orderSn)) {

            LineOrder lineOrder = hystrixLineOrderServiceClient.getBySn(orderSn);

            System.out.println("*********************");
            System.out.println(lineOrder);
            System.out.println("*********************");

            orderService.changeStatus(lineOrder, OrderStatusEnum.getById(lineOrder.getStatus()), OrderStatusEnum.PAID);
            hystrixLineOrderServiceClient.save(jsonService.toJson(lineOrder));

        } else if (OrderSnUtils.isRoomOrderSn(orderSn)) {

            RoomOrder roomOrder = hystrixRoomOrderServiceClient.getBySn(orderSn);
            orderService.changeStatus(roomOrder, OrderStatusEnum.getById(roomOrder.getStatus()), OrderStatusEnum.PAID);
            hystrixLineOrderServiceClient.save(jsonService.toJson(roomOrder));

        } else if (OrderSnUtils.isTicketOrderSn(orderSn)) {

            TicketOrder ticketOrder = hystrixTicketOrderServiceClient.getBySn(orderSn);
            orderService.changeStatus(ticketOrder, OrderStatusEnum.getById(ticketOrder.getStatus()), OrderStatusEnum.PAID);
            hystrixTicketOrderServiceClient.save(jsonService.toJson(ticketOrder));

        }
    }

    @Override
    public void fail(String merOrderId, PayTypeEnum quick) {

    }
}
