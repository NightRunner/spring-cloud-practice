package org.nr.tour.repository;

import org.nr.tour.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
}
