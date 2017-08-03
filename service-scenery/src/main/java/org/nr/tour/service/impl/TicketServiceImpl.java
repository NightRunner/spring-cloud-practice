package org.nr.tour.service.impl;

import org.nr.tour.BaseServiceImpl;
import org.nr.tour.domain.Ticket;
import org.nr.tour.repository.TicketRepository;
import org.nr.tour.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Service
public class TicketServiceImpl extends BaseServiceImpl<Ticket> implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    protected JpaRepository<Ticket, String> getRepository() {
        return ticketRepository;
    }

    @Override
    public List<Ticket> getBySceneryId(String sceneryId) {
        return ticketRepository.findBySceneryId(sceneryId);
    }

}
