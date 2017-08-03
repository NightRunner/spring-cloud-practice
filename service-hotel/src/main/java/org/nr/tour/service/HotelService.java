package org.nr.tour.service;

import org.nr.tour.BaseService;
import org.nr.tour.domain.Hotel;
import org.springframework.data.domain.Page;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public interface HotelService extends BaseService<Hotel> {
    Page<Hotel> findPageByNameLike(String keyword, Integer page, Integer size);
}
