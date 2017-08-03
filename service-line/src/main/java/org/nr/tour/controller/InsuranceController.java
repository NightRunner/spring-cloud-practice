package org.nr.tour.controller;

import org.nr.tour.common.controller.AbstractCRUDController;
import org.nr.tour.constant.ServiceConstants;
import org.nr.tour.domain.Insurance;
import org.nr.tour.repository.InsuranceRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@RefreshScope
@Api
@RestController
@RequestMapping(ServiceConstants.LINE_SERVICE_PATH_INSURANCE)
public class InsuranceController extends AbstractCRUDController<Insurance, String> {

    @Autowired
    InsuranceRepository insuranceRepository;

    @Override
    protected JpaRepository<Insurance, String> getRepository() {
        return insuranceRepository;
    }

}
