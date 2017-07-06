package org.nr.tour.service;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public interface VerifyCodeService {

    String newOne(String owner, Integer type, Integer expireMills);

    Boolean verify(String owner, String code, Integer type);
}
