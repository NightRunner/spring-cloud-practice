package org.nr.tour.api.service;

import org.nr.tour.domain.BaseOrder;
import org.nr.tour.domain.OrderStatusEnum;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public interface OrderService {

    int ORDER_STATUS_ALL = 0;
    int ORDER_STATUS_UN_PAID = 1;
    int ORDER_STATUS_COMPLETED = 2;
    int ORDER_STATUS_CANCEL = 3;

    boolean canChangeStatus(OrderStatusEnum fromStatus, OrderStatusEnum toStatus);

    void changeStatus(BaseOrder order, OrderStatusEnum fromStatus, OrderStatusEnum toStatus);

    Integer[] getRealOrderStatues(Integer status);
}
