package org.nr.tour.rpc;

import org.nr.tour.common.service.AbstractServiceDefinition;
import org.nr.tour.constant.ServiceConstants;
import org.nr.tour.domain.Scenery;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@FeignClient(ServiceConstants.SCENERY_SERVICE)
public interface SceneryServiceClient extends AbstractServiceDefinition<Scenery, String> {

}
