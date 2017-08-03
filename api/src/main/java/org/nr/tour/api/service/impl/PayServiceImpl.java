package org.nr.tour.api.service.impl;

import org.nr.tour.api.service.OrderPayNotifyService;
import org.nr.tour.api.service.PayService;
import org.nr.tour.common.util.JsonService;
import org.nr.tour.domain.PayTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Service
public class PayServiceImpl implements PayService {
    @Autowired
    OrderPayNotifyService orderPayNotifyService;
    @Autowired
    JsonService jsonService;

    @Override
    public void updateOrderStatus(String orderId) {
        orderPayNotifyService.success("orderId", PayTypeEnum.QUICK);
        orderPayNotifyService.fail("orderId", PayTypeEnum.QUICK);
    }

    @Override
    public String getPayString(String orderSn, Double price) {
        //// TODO: 2017-08-03
        return "";
    }
}
