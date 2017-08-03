package org.nr.tour.repository;

import org.nr.tour.domain.LineOrderInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Repository
public interface LineOrderInsuranceRepository extends JpaRepository<LineOrderInsurance, String> {
    List<LineOrderInsurance> findByLineOrderId(String orderId);
}
