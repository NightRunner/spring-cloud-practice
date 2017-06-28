package org.nr.tour.common.service;

import org.nr.tour.domain.HotelSupportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public interface HotelSupportServiceDefinition {

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    void save(@RequestParam("hotelId") String hotelId, @RequestParam("serviceIds") List<String> serviceIds);

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    List<HotelSupportService> getById(@RequestParam("hotelId") String hotelId);
}
