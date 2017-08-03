package org.nr.tour.rpc;

import org.nr.tour.common.service.AbstractServiceDefinition;
import org.nr.tour.constant.ServiceConstants;
import org.nr.tour.domain.StorageObject;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by Administrator on 2017/7/26.
 */
@FeignClient(ServiceConstants.OSS_SERVICE)
public interface OSSServiceClient extends AbstractServiceDefinition<StorageObject, String> {
}
