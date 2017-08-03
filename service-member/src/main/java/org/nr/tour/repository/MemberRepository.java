package org.nr.tour.repository;

import org.nr.tour.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    List<Member> findByLoginNameAndPassword(String loginName, String password);

    Member findByMobilePhone(String mobilePhone);

    Member findByToken(String token);

    List<Member> findByMobilePhoneAndPassword(String mobilePhone, String md5password);
}
