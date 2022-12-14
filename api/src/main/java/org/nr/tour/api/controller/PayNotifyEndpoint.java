package org.nr.tour.api.controller;

import org.nr.tour.api.service.OrderPayNotifyService;
import org.nr.tour.common.util.JsonService;
import org.nr.tour.domain.PayTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@RestController
public class PayNotifyEndpoint {

    @Autowired
    OrderPayNotifyService payNotifyService;
    @Autowired
    JsonService jsonService;

    @RequestMapping(value = "pay/notify", method = RequestMethod.POST)
    @ResponseBody
    public Object payQuickNotify(HttpServletRequest request, HttpServletResponse response) {

        payNotifyService.success("orderId", PayTypeEnum.QUICK);
        payNotifyService.fail("orderId", PayTypeEnum.QUICK);

        return Boolean.TRUE;
    }

}
