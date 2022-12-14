package org.nr.tour.domain;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public enum PayTypeEnum {

    QUICK("快捷支付", 19);

    private String name;
    private Integer id;

    PayTypeEnum(String name, Integer id) {
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
