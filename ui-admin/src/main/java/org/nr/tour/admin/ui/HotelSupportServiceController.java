package org.nr.tour.admin.ui;

import org.nr.tour.rpc.hystrix.HystrixWrappedHotelSupportServiceClient;
import org.nr.tour.rpc.hystrix.HystrixWrappedSupportServiceCategoryServiceClient;
import org.nr.tour.common.util.JsonService;
import org.nr.tour.domain.HotelSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Controller
@RequestMapping("/hotel/support/service")
public class HotelSupportServiceController {

    @Autowired
    HystrixWrappedHotelSupportServiceClient hystrixWrappedHotelSupportServiceClient;

    @Autowired
    HystrixWrappedSupportServiceCategoryServiceClient hystrixWrappedSupportServiceCategoryServiceClient;

    @Autowired
    JsonService jsonService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void save(@RequestParam("hotelId") String hotelId, @RequestParam("serviceIds") List<String> serviceIds) {
        hystrixWrappedHotelSupportServiceClient.save(hotelId, serviceIds);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public List<HotelSupportService> get(@RequestParam("hotelId") String hotelId) {
        return hystrixWrappedHotelSupportServiceClient.getById(hotelId);
    }

}
