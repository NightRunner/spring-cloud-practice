package org.nr.tour.service;

import org.nr.tour.BaseOrderService;
import org.nr.tour.BaseService;
import org.nr.tour.domain.OrderStatusEnum;
import org.nr.tour.domain.TicketOrder;
import org.nr.tour.domain.TicketOrderVisitor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public interface TicketOrderService extends BaseOrderService<TicketOrder> {
    void updateVisitors(String orderId, List<TicketOrderVisitor> visitors);
}
