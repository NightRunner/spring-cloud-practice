package org.nr.tour.repository;

import org.nr.tour.domain.HotelSupportService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Repository
public interface HotelSupportServiceRepository extends JpaRepository<HotelSupportService, String> {
    @Query("from HotelSupportService a where a.hotel.id = :hotelId")
    List<HotelSupportService> findByHotelId(@Param("hotelId") String hotelId);
}
