package org.nr.tour.rpc;

import org.nr.tour.common.service.LineServiceDefinition;
import org.nr.tour.constant.ServiceConstants;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@FeignClient(ServiceConstants.LINE_SERVICE)
public interface LineServiceClient extends LineServiceDefinition {

}
