package org.nr.tour.repository;

import org.nr.tour.domain.VerifyCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Repository
public interface VerifyCodeRepository extends JpaRepository<VerifyCode, String> {
    List<VerifyCode> findTop1ByOwnerAndCodeAndTypeAndUsedOrderByCreateTimeDesc(String owner, String code, Integer type, Boolean used);
}
