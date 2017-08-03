package org.nr.tour.repository;

import org.nr.tour.domain.RoomOrderGuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Repository
public interface RoomOrderGuestRepository extends JpaRepository<RoomOrderGuest, String> {
    List<RoomOrderGuest> findByRoomOrderId(String orderId);
}
