package org.nr.tour.domain;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public enum LineVisitorSexEnum {

    MALE("男", 0),
    FEMALE("女", 10);

    private String name;
    private Integer id;

    LineVisitorSexEnum(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getCode() {
        return id;
    }
}
