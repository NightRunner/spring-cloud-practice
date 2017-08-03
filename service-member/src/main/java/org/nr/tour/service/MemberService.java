package org.nr.tour.service;

import org.nr.tour.domain.Member;

/**
 * Created by Administrator on 2017/7/24.
 */
public interface MemberService {

    Member resetPassword(String mobilePhone, String pwd);

    Member memberInfo(String token);

    String phoneLogin(String mobilePhone);

    Member loginByLoginNameAndPassword(String username, String password);

    Member loginByMobilePhoneAndPassword(String mobilePhone, String password);

    Boolean logout(String token);

    Member createGuest();

    Member getByMobilePhone(String mobilePhone);

    Member getById(String id);
}
