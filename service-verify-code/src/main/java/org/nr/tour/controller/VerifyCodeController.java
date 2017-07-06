package org.nr.tour.controller;

import org.nr.tour.common.service.VerifyCodeServiceDefinition;
import org.nr.tour.service.VerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@RestController
public class VerifyCodeController implements VerifyCodeServiceDefinition {

    @Autowired
    VerifyCodeService verifyCodeService;

    public String newOne(@RequestParam("owner") String owner, @RequestParam("type") Integer type, @RequestParam("expireMills") Integer expireMills) {
        return verifyCodeService.newOne(owner, type, expireMills);
    }

    public Boolean verify(@RequestParam("owner") String owner, @RequestParam("code") String code, @RequestParam("type") Integer type) {
        return verifyCodeService.verify(owner, code, type);
    }

}
