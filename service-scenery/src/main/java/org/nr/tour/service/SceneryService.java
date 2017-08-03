package org.nr.tour.service;

import org.nr.tour.BaseService;
import org.nr.tour.domain.Scenery;
import org.springframework.data.domain.Page;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public interface SceneryService extends BaseService<Scenery> {
    Page<Scenery> findPageByNameLike(String keyword, Integer page, Integer size);
}
