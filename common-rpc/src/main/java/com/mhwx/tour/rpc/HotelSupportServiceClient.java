package org.nr.tour.rpc;

import org.nr.tour.common.service.HotelSupportServiceDefinition;
import org.nr.tour.constant.ServiceConstants;
import org.nr.tour.domain.HotelSupportService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@FeignClient(value = ServiceConstants.HOTEL_SERVICE, path = ServiceConstants.PATH_HOTEL_SUPPORT_SERVICE)
public interface HotelSupportServiceClient extends HotelSupportServiceDefinition {

}
