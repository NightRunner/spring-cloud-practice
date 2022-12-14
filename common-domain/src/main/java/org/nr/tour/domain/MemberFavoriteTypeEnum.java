package org.nr.tour.domain;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public enum MemberFavoriteTypeEnum {

    LINE("线路", 10),
    SCENERY("景点", 20),
    HOTEL("酒店", 30);

    private String name;
    private Integer id;

    MemberFavoriteTypeEnum(String name, Integer id) {
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
