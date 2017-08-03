package org.nr.tour.service;

import org.nr.tour.BaseService;
import org.nr.tour.domain.Line;
import org.springframework.data.domain.Page;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public interface LineService extends BaseService<Line> {
    Page<Line> findPageByNameLike(String keyword, Integer page, Integer size);
}
