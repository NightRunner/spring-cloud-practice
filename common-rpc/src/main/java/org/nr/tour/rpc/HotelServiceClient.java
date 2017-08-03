package org.nr.tour.rpc;

import org.nr.tour.common.service.HotelServiceDefinition;
import org.nr.tour.constant.ServiceConstants;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@FeignClient(ServiceConstants.HOTEL_SERVICE)
public interface HotelServiceClient extends HotelServiceDefinition {

}
