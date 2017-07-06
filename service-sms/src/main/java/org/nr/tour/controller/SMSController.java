package org.nr.tour.controller;

import org.nr.tour.common.service.SMSServiceDefinition;
import org.nr.tour.common.util.IdService;
import org.nr.tour.domain.SMSLog;
import org.nr.tour.repository.SMSLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@RestController
public class SMSController implements SMSServiceDefinition {

    private static final int TYPE_SMS_TEXT = 1;
    
    private static final int TYPE_SMS_VOICE = 2;

    @Autowired
    SMSLogRepository smsLogRepository;

    @Autowired
    IdService idService;

    @Override
    public Boolean sendText(@RequestParam String mobilePhone, @RequestParam String code) {
        // TODO: 2017-07-06 接入阿里大鱼
        System.out.println(String.format("向%s发送文本验证码:%s", mobilePhone, code));

        SMSLog smsLog = new SMSLog();

        smsLog.setId(idService.newOne());
        smsLog.setSendTime(new Date());
        smsLog.setCode(code);
        smsLog.setMobilePhone(mobilePhone);
        smsLog.setOriginResult(""); // TODO: 2017-07-06
        smsLog.setType(TYPE_SMS_TEXT);

        smsLogRepository.save(smsLog);

        return Boolean.TRUE;
    }

    @Override
    public Boolean sendVoice(@RequestParam String mobilePhone, @RequestParam String code) {
        // TODO: 2017-07-06 接入阿里大鱼
        System.out.println(String.format("向%s发送语音验证码:%s", mobilePhone, code));

        SMSLog smsLog = new SMSLog();

        smsLog.setId(idService.newOne());
        smsLog.setSendTime(new Date());
        smsLog.setCode(code);
        smsLog.setMobilePhone(mobilePhone);
        smsLog.setOriginResult(""); // TODO: 2017-07-06
        smsLog.setType(TYPE_SMS_VOICE);

        smsLogRepository.save(smsLog);

        return Boolean.TRUE;
    }
}
