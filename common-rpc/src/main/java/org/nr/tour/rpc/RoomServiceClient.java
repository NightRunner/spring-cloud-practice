package org.nr.tour.rpc;

import org.nr.tour.common.service.RoomServiceDefinition;
import org.nr.tour.constant.ServiceConstants;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@FeignClient(value = ServiceConstants.HOTEL_SERVICE, path = ServiceConstants.HOTEL_SERVICE_PATH_ROOM)
public interface RoomServiceClient extends RoomServiceDefinition {

}
