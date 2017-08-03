package org.nr.tour.api.service;

import org.nr.tour.api.service.impl.PayStatusEnum;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public interface PayService {

    String getPayString(String orderId, Double price);

    void updateOrderStatus(String orderId);

}
