package org.nr.tour.admin.ui;

import org.nr.tour.common.util.JsonService;
import org.nr.tour.constant.PageConstants;
import org.nr.tour.domain.Hotel;
import org.nr.tour.domain.PageImplWrapper;
import org.nr.tour.rpc.hystrix.HystrixDictServiceClient;
import org.nr.tour.rpc.hystrix.HystrixHotelServiceClient;
import org.nr.tour.rpc.hystrix.HystrixHotelSupportServiceClient;
import org.nr.tour.rpc.hystrix.HystrixSupportServiceCategoryServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Controller
@RequestMapping("hotel")
public class HotelController {

    private static final String HOTEL_TYPE = "hotelType";
    private static final String HOTEL_LEVEL = "hotelLevel";

    @Autowired
    HystrixHotelServiceClient hystrixHotelServiceClient;

    @Autowired
    HystrixDictServiceClient hystrixDictServiceClient;

    @Autowired
    HystrixHotelSupportServiceClient hystrixHotelSupportServiceClient;

    @Autowired
    JsonService jsonService;
    @Autowired
    private HystrixSupportServiceCategoryServiceClient hystrixSupportServiceCategoryServiceClient;

    @RequestMapping("/list")
    public String list(Model model,
                       @RequestParam(value = "page", required = false, defaultValue = PageConstants.DEFAULT_PAGE_NUMBER) Integer page,
                       @RequestParam(value = "size", required = false, defaultValue = PageConstants.DEFAULT_PAGE_SIZE) Integer size,
                       @RequestParam(value = "sort", required = false) List<String> sort) {
        final PageImplWrapper<Hotel> pageList = hystrixHotelServiceClient.getPage(page, size, sort);
        model.addAttribute("page", pageList);
        return "hotel/hotelList";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Hotel save(@ModelAttribute Hotel hotel, @RequestParam(value = "supportServiceIds", required = false) List<String> supportServiceIds) {
        Hotel savedHotel = hystrixHotelServiceClient.save(jsonService.toJson(hotel));

        hystrixHotelSupportServiceClient.save(savedHotel.getId(), supportServiceIds);

        return savedHotel;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Hotel get(@RequestParam("id") String id) {
        return hystrixHotelServiceClient.getById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Hotel delete(@RequestParam(value = "id") String id) {
        return hystrixHotelServiceClient.deleteById(id);
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form(Model model, @RequestParam(value = "id", required = false) String id) {

        Hotel hotel = null;
        if (StringUtils.isEmpty(id)) {
            hotel = new Hotel();
        } else {
            hotel = hystrixHotelServiceClient.getById(id);
        }

        model.addAttribute("entity", hotel);
        model.addAttribute("typeList", hystrixDictServiceClient.findByType(HOTEL_TYPE));
        model.addAttribute("levelList", hystrixDictServiceClient.findByType(HOTEL_LEVEL));

        return "hotel/hotelForm";
    }


}
