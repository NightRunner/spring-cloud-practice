package org.nr.tour.service.impl;

import org.nr.tour.BaseServiceImpl;
import org.nr.tour.domain.Hotel;
import org.nr.tour.repository.HotelRepository;
import org.nr.tour.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Service
public class HotelServiceImpl extends BaseServiceImpl<Hotel> implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    protected JpaRepository<Hotel, String> getRepository() {
        return hotelRepository;
    }
}
