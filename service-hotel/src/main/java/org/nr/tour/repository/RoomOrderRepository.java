package org.nr.tour.repository;

import org.nr.tour.BaseOrderRepository;
import org.nr.tour.domain.RoomOrder;
import org.springframework.stereotype.Repository;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Repository
public interface RoomOrderRepository extends BaseOrderRepository<RoomOrder> {
}
