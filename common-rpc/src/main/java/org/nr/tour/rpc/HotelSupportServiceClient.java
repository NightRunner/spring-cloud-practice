package org.nr.tour.rpc;

import org.nr.tour.common.service.HotelSupportServiceDefinition;
import org.nr.tour.constant.ServiceConstants;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@FeignClient(value = ServiceConstants.HOTEL_SERVICE, path = ServiceConstants.PATH_HOTEL_SUPPORT_SERVICE)
public interface HotelSupportServiceClient extends HotelSupportServiceDefinition {

}
