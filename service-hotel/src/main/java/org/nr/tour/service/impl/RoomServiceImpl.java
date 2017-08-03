package org.nr.tour.service.impl;

import org.nr.tour.BaseServiceImpl;
import org.nr.tour.domain.Room;
import org.nr.tour.repository.RoomRepository;
import org.nr.tour.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Service
public class RoomServiceImpl extends BaseServiceImpl<Room> implements RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Override
    protected JpaRepository<Room, String> getRepository() {
        return roomRepository;
    }

    @Override
    public List<Room> getByHotelId(String hotelId) {
        return roomRepository.getByHotelId(hotelId);
    }
}
