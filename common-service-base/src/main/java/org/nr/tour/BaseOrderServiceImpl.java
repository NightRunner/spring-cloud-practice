package org.nr.tour;

import org.nr.tour.domain.BaseOrder;
import org.nr.tour.domain.OrderStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */

public abstract class BaseOrderServiceImpl<T extends BaseOrder> extends BaseServiceImpl<T> implements BaseOrderService<T> {

    abstract protected BaseOrderRepository<T> getRepository();

    @Override
    public List<T> findByMemberIdAndStatues(String memberId, List<OrderStatusEnum> statusEnumList) {
        List<Integer> statusList = new ArrayList<Integer>();

        for (OrderStatusEnum statusEnum : statusEnumList) {
            statusList.add(statusEnum.getId());
        }

        return getRepository().findByMemberIdAndStatusIn(memberId, statusList);
    }

    @Override
    public T getBySn(String sn) {
        return getRepository().findBySn(sn);
    }

    @Override
    public Page<T> findByMemberId(String memberId, PageRequest pageRequest) {
        return getRepository().findByMemberId(memberId, pageRequest);
    }

    @Override
    public Page<T> findPageByMemberId(String memberId, Integer page, Integer size) {
        return getRepository().findByMemberId(memberId, new PageRequest(page, size));
    }

    @Override
    public Page<T> findPageByMemberIdAndStatues(String memberId, List<OrderStatusEnum> statusEnumList, Integer page, Integer size) {
        List<Integer> statusList = new ArrayList<Integer>();

        for (OrderStatusEnum statusEnum : statusEnumList) {
            statusList.add(statusEnum.getId());
        }

        return getRepository().findByMemberIdAndStatusIn(memberId, statusList, new PageRequest(page, size));
    }

}
