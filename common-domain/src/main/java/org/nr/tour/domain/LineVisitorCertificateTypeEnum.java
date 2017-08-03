package org.nr.tour.domain;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public enum LineVisitorCertificateTypeEnum {

    ID("身份证", 0),
    PASSPORT("护照", 10),
    OTHER("其他", 99);

    private String name;
    private Integer id;

    LineVisitorCertificateTypeEnum(String name, Integer id) {
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
