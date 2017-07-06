package org.nr.tour.common.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public interface SMSServiceDefinition {

    @RequestMapping(value = "new")
    Boolean sendText(@RequestParam("mobilePhone") String mobilePhone, @RequestParam("code") String code);

    @RequestMapping(value = "verify")
    Boolean sendVoice(@RequestParam("mobilePhone") String mobilePhone, @RequestParam("code") String code);
}
