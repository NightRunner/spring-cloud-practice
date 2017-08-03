package org.nr.tour.api.vo;

import org.nr.tour.domain.LineVisitorTypeEnum;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public class BaseOrderVisitorVO extends BaseVO {

    /**
     * 姓名
     */
    private String name;

    /**
     * 成人 or 小孩
     *
     * @see LineVisitorTypeEnum
     */
    private Integer type;

    /**
     * 证件类型
     */
    private Integer certificateType;

    /**
     * 证件号码
     */
    private String certificateNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(Integer certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }
}
