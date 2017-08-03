package org.nr.tour.service.impl;

import org.nr.tour.BaseServiceImpl;
import org.nr.tour.domain.RoomOrderGuest;
import org.nr.tour.repository.RoomOrderGuestRepository;
import org.nr.tour.service.RoomOrderGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Service
public class RoomGuestServiceImpl extends BaseServiceImpl<RoomOrderGuest> implements RoomOrderGuestService {

    @Autowired
    RoomOrderGuestRepository roomOrderGuestRepository;

    @Override
    protected JpaRepository<RoomOrderGuest, String> getRepository() {
        return roomOrderGuestRepository;
    }

    @Override
    public List<RoomOrderGuest> findByRoomOrderId(String orderId) {
        return roomOrderGuestRepository.findByRoomOrderId(orderId);
    }
}
