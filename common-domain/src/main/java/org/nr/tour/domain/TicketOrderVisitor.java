package org.nr.tour.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 景区门票订单游客
 *
 * @author chenhaiyang <690732060@qq.com>
 */
@Entity
@Table(name = "ticket_order_visitor")
public class TicketOrderVisitor extends BaseOrderVisitor {
}
