package org.nr.tour.repository;

import org.nr.tour.domain.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Repository
public interface HotelRepository extends JpaRepository<Hotel, String> {
    List<Hotel> findByRecommend(Boolean bool);

    Page<Hotel> findByNameLike(String keyword, Pageable pageable);
}
