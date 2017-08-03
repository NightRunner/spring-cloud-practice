package org.nr.tour.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 线路订单游客
 *
 * @author chenhaiyang <690732060@qq.com>
 */
@Entity
@Table(name = "line_order_visitor")
public class LineOrderVisitor extends BaseOrderVisitor {

    /**
     * 性别
     */
    @Column(name = "sex")
    private Integer sex;

    /**
     * 手机号
     */
    @Column(name = "mobile_phone", length = 16)
    private String mobilePhone;

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
