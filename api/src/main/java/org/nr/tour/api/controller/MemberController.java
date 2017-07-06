package org.nr.tour.api.controller;

import org.nr.tour.api.support.APIResult;
import org.nr.tour.api.support.ErrorCode;
import org.nr.tour.api.support.ServiceException;
import org.nr.tour.rpc.hystrix.HystrixSMSServiceClient;
import org.nr.tour.rpc.hystrix.HystrixVerifyCodeServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@RestController
public class MemberController {

    private static final int TYPE_VERIFY_CODE_LOGIN = 101;
    private static final int TYPE_VERIFY_CODE_REG = 102;

    private static final int MINUTE = 1000 * 60;

    @Autowired
    HystrixVerifyCodeServiceClient hystrixVerifyCodeServiceClient;

    @Autowired
    HystrixSMSServiceClient hystrixSMSServiceClient;

    @RequestMapping(value = "member/send/reg/code")
    public Object sendRegisterSMSCode(@RequestParam String mobilePhone) {

        String code = hystrixVerifyCodeServiceClient.newOne(mobilePhone, TYPE_VERIFY_CODE_REG, 30 * MINUTE);

        hystrixSMSServiceClient.sendText(mobilePhone, code);

        return APIResult.createSuccess(mobilePhone);
    }

    @RequestMapping("member/send/login/code")
    public Object sendLoginSMSCode(@RequestParam String mobilePhone) {

        String code = hystrixVerifyCodeServiceClient.newOne(mobilePhone, TYPE_VERIFY_CODE_LOGIN, 30 * MINUTE);

        hystrixSMSServiceClient.sendText(mobilePhone, code);

        return APIResult.createSuccess();
    }

    @RequestMapping("member/reg")
    public Object register() {
        return APIResult.createSuccess();
    }

    @RequestMapping("member/phoneLogin")
    public Object phoneLogin(@RequestParam String mobilePhone, @RequestParam String code) {
        if (!hystrixVerifyCodeServiceClient.verify(mobilePhone, code, TYPE_VERIFY_CODE_LOGIN)) {
            throw new ServiceException(ErrorCode.INVALID_LOGIN_VERIFY_CODE);
        }
        return APIResult.createSuccess();
    }

    @RequestMapping("member/login")
    public Object login(@RequestParam String username, @RequestParam String password) {
        return APIResult.createSuccess();
    }

    @RequestMapping("member/logout")
    public Object logout(@RequestParam String token) {
        return APIResult.createSuccess();
    }
}
