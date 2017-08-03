package org.nr.tour.service;

import org.nr.tour.BaseOrderService;
import org.nr.tour.BaseService;
import org.nr.tour.domain.Insurance;
import org.nr.tour.domain.LineOrder;
import org.nr.tour.domain.LineOrderVisitor;
import org.nr.tour.domain.OrderStatusEnum;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public interface LineOrderService extends BaseOrderService<LineOrder> {
    void updateVisitors(String orderId, List<LineOrderVisitor> visitors);

    void updateInsurances(String orderId, List<Insurance> insurances);

}
