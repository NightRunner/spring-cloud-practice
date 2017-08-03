package org.nr.tour.api.controller;

import org.nr.tour.api.Constants;
import org.nr.tour.api.service.VoBuilderService;
import org.nr.tour.api.support.APIResult;
import org.nr.tour.api.support.ServiceException;
import org.nr.tour.api.vo.HotelDetailVO;
import org.nr.tour.api.vo.HotelItemVO;
import org.nr.tour.common.util.DateUtils;
import org.nr.tour.domain.Hotel;
import org.nr.tour.domain.PageImplWrapper;
import org.nr.tour.rpc.hystrix.HystrixHotelServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@RestController
public class HotelEndpoint {

    @Autowired
    HystrixHotelServiceClient hystrixHotelServiceClient;

    @Autowired
    VoBuilderService voBuilderService;

    @RequestMapping("hotel/recommend/list")
    public Object hotelRecommendList() {
        final List<Hotel> recommendList = hystrixHotelServiceClient.getRecommendList();

        List<HotelItemVO> hotelVOList = new ArrayList<HotelItemVO>();
        for (Hotel hotel : recommendList) {
            hotelVOList.add(voBuilderService.buildItemVO(hotel));
        }

        return APIResult.createSuccess(hotelVOList);
    }

    @RequestMapping("hotel/detail")
    public Object hotelDetail(@RequestParam String id) {
        Hotel hotel = hystrixHotelServiceClient.getById(id);
        HotelDetailVO vo = voBuilderService.buildDetailVO(hotel);
        return APIResult.createSuccess(vo);
    }

    @RequestMapping("hotel/search")
    public Object hotelSearchList(
            @RequestParam(name = "checkInTime", required = false) String checkInTimeString,
            @RequestParam(name = "leaveTime", required = false) String leaveTimeString,
            @RequestParam String city,
            @RequestParam(required = false, defaultValue = "") String keyword,
            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_NO) Integer pageNo,
            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE) Integer pageSize) {

        Date checkInTime = DateUtils.parseDate(checkInTimeString);
        Date leaveTime = DateUtils.parseDate(leaveTimeString);

        if (checkInTime == null) {
            throw new ServiceException("错误的入住时间");
        }
        if (leaveTime == null) {
            throw new ServiceException("错误的离店时间");
        }

        PageImplWrapper<Hotel> pageImplWrapper =
                hystrixHotelServiceClient.search(city, checkInTimeString, checkInTimeString,
                        StringUtils.isEmpty(keyword) ? "" : keyword, pageNo, pageSize);

        List<HotelItemVO> voList = new ArrayList<HotelItemVO>();
        for (Hotel hotel : pageImplWrapper.getContent()) {
            voList.add(voBuilderService.buildItemVO(hotel));
        }
        return APIResult.createSuccess(voBuilderService.buildPage(voList, !pageImplWrapper.isLast()));
    }

}
