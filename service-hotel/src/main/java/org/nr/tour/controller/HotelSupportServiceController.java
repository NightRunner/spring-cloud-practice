package org.nr.tour.controller;

import org.nr.tour.common.service.HotelSupportServiceDefinition;
import org.nr.tour.common.util.IdService;
import org.nr.tour.constant.ServiceConstants;
import org.nr.tour.domain.Hotel;
import org.nr.tour.domain.HotelSupportService;
import org.nr.tour.repository.HotelRepository;
import org.nr.tour.repository.HotelSupportServiceRepository;
import org.nr.tour.repository.SupportServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@RestController
@RequestMapping(ServiceConstants.PATH_HOTEL_SUPPORT_SERVICE)
public class HotelSupportServiceController implements HotelSupportServiceDefinition {

    @Autowired
    IdService idService;

    @Autowired
    HotelSupportServiceRepository hotelSupportServiceRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    SupportServiceRepository supportServiceRepository;

    @Override
    public void save(@RequestParam("hotelId") String hotelId, @RequestParam("serviceIds") List<String> serviceIds) {

        hotelSupportServiceRepository.delete(hotelSupportServiceRepository.findByHotelId(hotelId));

        Hotel hotel = hotelRepository.findOne(hotelId);
        for (String serviceId : serviceIds) {
            HotelSupportService hotelSupportService = new HotelSupportService();
            hotelSupportService.setId(idService.newOne());
            hotelSupportService.setHotel(hotel);
            hotelSupportService.setService(supportServiceRepository.findOne(serviceId));
            hotelSupportServiceRepository.save(hotelSupportService);
        }

    }

    @Override
    public List<HotelSupportService> getById(@RequestParam("hotelId") String hotelId) {
        return hotelSupportServiceRepository.findByHotelId(hotelId);
    }
}
