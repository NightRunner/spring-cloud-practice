package org.nr.tour.domain;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public enum HotelStatusEnum {

    A;

    private Integer id;
    private String code;
    private String showName;

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getShowName() {
        return showName;
    }
}
