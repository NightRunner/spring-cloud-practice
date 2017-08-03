package org.nr.tour.repository;

import org.nr.tour.domain.LineOrderVisitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Repository
public interface LineOrderVisitorRepository extends JpaRepository<LineOrderVisitor, String> {
    List<LineOrderVisitor> findByOrderId(String lineOrderId);
}
