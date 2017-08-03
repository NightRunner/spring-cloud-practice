package org.nr.tour.domain;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public enum LineOrderProcessTypeEnum {

    MANUAL(10, "人工处理"), AUTO(20, "自动处理");

    private Integer id;
    private String name;

    LineOrderProcessTypeEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
