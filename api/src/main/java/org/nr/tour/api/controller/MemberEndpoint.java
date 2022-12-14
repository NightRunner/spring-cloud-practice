package org.nr.tour.api.controller;

import org.nr.tour.api.Constants;
import org.nr.tour.api.service.VoBuilderService;
import org.nr.tour.api.support.APIResult;
import org.nr.tour.api.support.ErrorCode;
import org.nr.tour.api.support.ServiceException;
import org.nr.tour.api.vo.MemberIndexVO;
import org.nr.tour.common.util.JsonService;
import org.nr.tour.common.util.MD5Util;
import org.nr.tour.domain.Member;
import org.nr.tour.rpc.hystrix.HystrixMemberServiceClient;
import org.nr.tour.rpc.hystrix.HystrixSMSServiceClient;
import org.nr.tour.rpc.hystrix.HystrixVerifyCodeServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@RestController
public class MemberEndpoint {


    private static final int MINUTE = 1000 * 60;

    @Autowired
    HystrixVerifyCodeServiceClient hystrixVerifyCodeServiceClient;
    @Autowired
    HystrixMemberServiceClient hystrixMemberServiceClient;
    @Autowired
    HystrixSMSServiceClient hystrixSMSServiceClient;
    @Autowired
    VoBuilderService voBuilderService;
    @Autowired
    private JsonService jsonService;

    @RequestMapping(value = "member/sendCode")
    public Object sendRegisterSMSCode(@RequestParam String mobilePhone) {

        String code = hystrixVerifyCodeServiceClient.newOne(mobilePhone, Constants.TYPE_VERIFY_CODE_ALL, 30 * MINUTE);

        hystrixSMSServiceClient.sendText(mobilePhone, code);

        Map<String, String> map = new HashMap<String, String>();

        map.put("mobilePhone", mobilePhone);
        map.put("code", code);

        return APIResult.createSuccess(map);
    }

    @RequestMapping("member/reg")
    public Object register(@RequestParam String mobilePhone, @RequestParam String password, @RequestParam String code) {
        if (!hystrixVerifyCodeServiceClient.verify(mobilePhone, code, Constants.TYPE_VERIFY_CODE_ALL)) {
            throw new ServiceException(ErrorCode.TOKEN_EXPIRED);
        }

        final Member alreadyExistMember = hystrixMemberServiceClient.getByMobilePhone(mobilePhone);
        if (alreadyExistMember != null) {
            throw new ServiceException("???????????????????????????");
        }

        Member member = new Member();
        member.setMobilePhone(mobilePhone);
        member.setPassword(MD5Util.md5(password));
        member.setRegisterTime(new Date());

        Member result = hystrixMemberServiceClient.save(jsonService.toJson(member));
        if (result == null) {
            throw new ServiceException(ErrorCode.COMMON);
        }
        return APIResult.createSuccess(Boolean.TRUE);
    }

    @RequestMapping("member/phoneAndCodeLogin")
    public Object phoneAndCodeLogin(@RequestParam String mobilePhone, @RequestParam String code) {
        if (!hystrixVerifyCodeServiceClient.verify(mobilePhone, code, Constants.TYPE_VERIFY_CODE_ALL)) {
            throw new ServiceException(ErrorCode.TOKEN_EXPIRED);
        }

        String token = hystrixMemberServiceClient.login(mobilePhone);

        if (StringUtils.isEmpty(token)) {
            throw new ServiceException("????????????", ErrorCode.COMMON);
        }

        return APIResult.createSuccess(token);
    }

    @RequestMapping("member/login")
    public Object usernameAndPwdLogin(@RequestParam String username, @RequestParam String password) {
        Member member = null;
        final String md5Password = MD5Util.md5(password);
        member = hystrixMemberServiceClient.phoneAndPwdLogin(username, md5Password);
        if (member == null) {
            member = hystrixMemberServiceClient.usernameAndPwdLogin(username, md5Password);
        }
        if (member == null) {
            throw new ServiceException("????????????", ErrorCode.COMMON);
        }
        return APIResult.createSuccess(member);
    }

    @RequestMapping("member/logout")
    public Object logout(@RequestParam String token) {
        return APIResult.createSuccess(hystrixMemberServiceClient.logout(token));
    }

    @RequestMapping("member/info")
    public Object memberInfo(@RequestParam String token) {
        Member member = hystrixMemberServiceClient.getByToken(token);
        return APIResult.createSuccess(member);
    }

    @RequestMapping("member/index")
    public Object memberIndex(@RequestParam(required = false, defaultValue = "") String token) {

        MemberIndexVO vo = null;

        if (StringUtils.isEmpty(token)) {
            vo = voBuilderService.buildEmptyMemberIndexVO();
        } else {
            Member member = hystrixMemberServiceClient.getByToken(token);
            vo = voBuilderService.buildMemberIndexVO(member);
        }

        return APIResult.createSuccess(vo);
    }

    @RequestMapping("member/resetPwd")
    public Object resetPwd(@RequestParam String mobilePhone, @RequestParam String code, @RequestParam String newPassword) {
        if (!hystrixVerifyCodeServiceClient.verify(mobilePhone, code, Constants.TYPE_VERIFY_CODE_ALL)) {
            throw new ServiceException(ErrorCode.TOKEN_EXPIRED);
        }
        Member member = hystrixMemberServiceClient.resetPassword(mobilePhone, MD5Util.md5(newPassword));
        if (member == null) {
            throw new ServiceException("??????????????????", ErrorCode.COMMON);
        }
        return APIResult.createSuccess();
    }

}
