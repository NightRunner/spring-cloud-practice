package org.nr.tour;

import org.nr.tour.domain.BaseOrder;
import org.nr.tour.domain.OrderStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public interface BaseOrderService<T extends BaseOrder> extends BaseService<T> {

    Page<T> findByMemberId(String memberId, PageRequest pageRequest);

    Page<T> findPageByMemberIdAndStatues(String memberId, List<OrderStatusEnum> statusEnumList, Integer page, Integer size);

    Page<T> findPageByMemberId(String memberId, Integer page, Integer size);

    List<T> findByMemberIdAndStatues(String memberId, List<OrderStatusEnum> statusEnumList);

    T getBySn(String sn);

}
