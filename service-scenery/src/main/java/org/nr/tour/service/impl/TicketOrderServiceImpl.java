package org.nr.tour.service.impl;

import org.nr.tour.BaseOrderRepository;
import org.nr.tour.BaseOrderServiceImpl;
import org.nr.tour.domain.TicketOrder;
import org.nr.tour.domain.TicketOrderVisitor;
import org.nr.tour.domain.util.EntityUtils;
import org.nr.tour.repository.TicketOrderRepository;
import org.nr.tour.repository.TicketOrderVisitorRepository;
import org.nr.tour.service.TicketOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Service
public class TicketOrderServiceImpl extends BaseOrderServiceImpl<TicketOrder> implements TicketOrderService {

    @Autowired
    TicketOrderRepository ticketOrderRepository;

    @Autowired
    TicketOrderVisitorRepository ticketOrderVisitorRepository;

    @Override
    protected BaseOrderRepository<TicketOrder> getRepository() {
        return ticketOrderRepository;
    }

    @Override
    public void updateVisitors(String orderId, List<TicketOrderVisitor> visitors) {
        ticketOrderVisitorRepository.delete(ticketOrderVisitorRepository.findByOrderId(orderId));

        for (TicketOrderVisitor visitor : visitors) {
            visitor.setOrderId(orderId);
            EntityUtils.preSave(visitor);
            ticketOrderVisitorRepository.save(visitor);
        }
    }
}
