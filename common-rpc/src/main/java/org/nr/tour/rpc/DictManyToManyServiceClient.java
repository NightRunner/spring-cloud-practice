package org.nr.tour.rpc;

import org.nr.tour.common.service.DictManyToManyServiceDefinition;
import org.nr.tour.constant.ServiceConstants;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@FeignClient(value = ServiceConstants.DICT_SERVICE, path = ServiceConstants.DICT_SERVICE_PATH_MANY_TO_MANY)
public interface DictManyToManyServiceClient extends DictManyToManyServiceDefinition {
}
