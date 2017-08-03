package org.nr.tour.controller;

import org.nr.tour.common.controller.AbstractCRUDController;
import org.nr.tour.domain.StorageObject;
import org.nr.tour.repository.StorageObjectRepository;
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
public class StorageObjectController extends AbstractCRUDController<StorageObject, String> {

    @Autowired
    StorageObjectRepository storageObjectRepository;

    @Override
    protected JpaRepository<StorageObject, String> getRepository() {
        return storageObjectRepository;
    }
}
