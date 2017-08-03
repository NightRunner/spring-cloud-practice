package org.nr.tour.rpc;

import org.nr.tour.common.service.RoomOrderServiceDefinition;
import org.nr.tour.constant.ServiceConstants;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@FeignClient(value = ServiceConstants.HOTEL_SERVICE, path = ServiceConstants.HOTEL_SERVICE_PATH_ROOM_ORDER)
public interface RoomOrderServiceClient extends RoomOrderServiceDefinition {

}
