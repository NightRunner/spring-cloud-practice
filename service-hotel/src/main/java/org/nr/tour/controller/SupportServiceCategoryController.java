package org.nr.tour.controller;

import org.nr.tour.common.controller.AbstractCRUDController;
import org.nr.tour.constant.ServiceConstants;
import org.nr.tour.domain.SupportServiceCategory;
import org.nr.tour.repository.SupportServiceCategoryRepository;
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
@RequestMapping(ServiceConstants.PATH_SERVICE_CATEGORY)
public class SupportServiceCategoryController extends AbstractCRUDController<SupportServiceCategory, String> {

    @Autowired
    SupportServiceCategoryRepository supportServiceCategoryRepository;


    @Override
    protected JpaRepository<SupportServiceCategory, String> getRepository() {
        return supportServiceCategoryRepository;
    }
}
