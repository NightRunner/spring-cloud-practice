package org.nr.tour.api.service.impl;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public enum PayStatusEnum {

    UN_KNOWN("未知", -99),
    FAIL("支付失败", -1),
    NOT_PAY("未支付", 0),
    PAID("支付成功", 1);

    Integer id;
    String name;

    PayStatusEnum(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}
