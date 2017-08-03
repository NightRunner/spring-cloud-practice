package org.nr.tour.api.service;

import org.nr.tour.domain.PayTypeEnum;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public interface OrderPayNotifyService {
    void success(String orderId, PayTypeEnum payTypeEnum) ;

    void fail(String merOrderId, PayTypeEnum quick);
}
