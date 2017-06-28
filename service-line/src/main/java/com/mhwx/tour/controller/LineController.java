package org.nr.tour.controller;

import org.nr.tour.common.controller.AbstractCRUDController;
import org.nr.tour.domain.Line;
import org.nr.tour.repository.LineRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@RefreshScope
@Api
@RestController
public class LineController extends AbstractCRUDController<Line, String> {

    @Autowired
    LineRepository lineRepository;

    @Override
    protected JpaRepository<Line, String> getRepository() {
        return lineRepository;
    }
}
