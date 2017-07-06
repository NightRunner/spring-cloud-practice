package org.nr.tour.repository;

import org.nr.tour.domain.Hotel;
import org.nr.tour.domain.Visa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Repository
public interface VisaRepository extends JpaRepository<Visa, String> {
}
