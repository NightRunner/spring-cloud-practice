package org.nr.tour.api.controller;

import org.nr.tour.api.service.PayService;
import org.nr.tour.api.service.impl.PayStatusEnum;
import org.nr.tour.api.support.APIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@RestController
public class OrderPayEndpoint {

    @Autowired
    PayService payService;

    @RequestMapping("order/pay/status/update")
    public Object lineOrderPayStatusUpdate(@RequestParam("orderId") String orderId) {

        payService.updateOrderStatus(orderId);

        return APIResult.createSuccess();
    }

}
