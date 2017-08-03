package org.nr.tour.service.impl;

import org.nr.tour.BaseServiceImpl;
import org.nr.tour.domain.Scenery;
import org.nr.tour.repository.SceneryRepository;
import org.nr.tour.service.SceneryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Service
public class SceneryServiceImpl extends BaseServiceImpl<Scenery> implements SceneryService {

    @Autowired
    SceneryRepository sceneryRepository;

    @Override
    protected JpaRepository<Scenery, String> getRepository() {
        return sceneryRepository;
    }


    @Override
    public Page<Scenery> findPageByNameLike(String keyword, Integer page, Integer size) {
        if (StringUtils.isEmpty(keyword)) {
            return sceneryRepository.findAll(new PageRequest(page, size));
        }
        return sceneryRepository.findByNameLike("%" + keyword + "%", new PageRequest(page, size));
    }
}
