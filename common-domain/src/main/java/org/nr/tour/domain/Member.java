package org.nr.tour.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Entity
@Table(name = "member")
public class Member extends IDEntity {

    /**
     * 手机号
     */
    @Column(name = "mobile_phone", length = 32)
    private String mobilePhone;

    /**
     * 昵称
     */
    @Column(name = "nick_name", length = 255)
    private String nickName;

    /**
     * 密码
     */
    @JsonIgnore
    @Column(length = 255)
    private String password;

    /**
     * 登陆名
     */
    @Column(name = "login_name", length = 255)
    private String loginName;

    /**
     * 注册时间
     */
    @Column(name = "register_time")
    private Date registerTime;

    @Column(name = "email", length = 128)
    private String email;

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
