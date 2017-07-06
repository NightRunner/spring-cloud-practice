package org.nr.tour.common.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public interface VerifyCodeServiceDefinition {

    @RequestMapping(value = "new")
    String newOne(@RequestParam("owner") String owner, @RequestParam("type") Integer type, @RequestParam("expireMills") Integer expireMills);

    @RequestMapping(value = "verify")
    Boolean verify(@RequestParam("owner") String owner, @RequestParam("code") String code, @RequestParam("type") Integer type);
}
