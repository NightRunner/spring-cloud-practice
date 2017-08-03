package org.nr.tour;

import org.nr.tour.domain.BaseOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@NoRepositoryBean
public interface BaseOrderRepository<T extends BaseOrder> extends JpaRepository<T, String> {

    Page<T> findByMemberId(String memberId, Pageable pageable);

    Page<T> findByMemberIdAndStatus(String memberId, Integer status, Pageable pageable);

    Page<T> findByMemberIdAndStatusIn(String memberId, List<Integer> statusList, Pageable pageable);

    List<T> findByMemberIdAndStatusIn(String memberId, List<Integer> statusList);

    T findBySn(String sn);

}
