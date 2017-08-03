package org.nr.tour.service;

import org.nr.tour.BaseService;
import org.nr.tour.domain.Ticket;
import org.nr.tour.domain.TicketOrder;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public interface TicketService extends BaseService<Ticket> {
    List<Ticket> getBySceneryId(String sceneryId);
}
