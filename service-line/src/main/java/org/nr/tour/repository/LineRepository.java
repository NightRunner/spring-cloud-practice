package org.nr.tour.repository;

import org.nr.tour.domain.Line;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public interface LineRepository extends JpaRepository<Line, String> {
}
