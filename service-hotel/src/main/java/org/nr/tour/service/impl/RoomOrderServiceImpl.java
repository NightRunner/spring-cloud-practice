package org.nr.tour.service.impl;

import org.nr.tour.BaseOrderRepository;
import org.nr.tour.BaseOrderServiceImpl;
import org.nr.tour.domain.RoomOrder;
import org.nr.tour.repository.RoomOrderRepository;
import org.nr.tour.service.RoomOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Service
public class RoomOrderServiceImpl extends BaseOrderServiceImpl<RoomOrder> implements RoomOrderService {

    @Autowired
    RoomOrderRepository roomOrderRepository;

    @Override
    protected BaseOrderRepository<RoomOrder> getRepository() {
        return roomOrderRepository;
    }

}
