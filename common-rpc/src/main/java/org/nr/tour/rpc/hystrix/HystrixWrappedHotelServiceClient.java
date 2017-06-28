package org.nr.tour.rpc.hystrix;

import com.google.common.collect.Lists;
import org.nr.tour.rpc.HotelServiceClient;
import org.nr.tour.domain.Hotel;
import org.nr.tour.domain.PageImplWrapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Service
public class HystrixWrappedHotelServiceClient implements HotelServiceClient {

    @Autowired
    private HotelServiceClient hotelServiceClient;

    @Override
    @HystrixCommand(fallbackMethod = "deleteFallBackCall")
    public Hotel deleteById(String id) {
        return hotelServiceClient.deleteById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "saveFallBackCall")
    public Hotel save(String dtoJson) {
        return hotelServiceClient.save(dtoJson);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getByIdFallBackCall")
    public Hotel getById(String id) {
        return hotelServiceClient.getById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getPageFallBackCall")
    public PageImplWrapper<Hotel> getPage(Integer page, Integer size, List<String> sort) {
        return hotelServiceClient.getPage(page, size,sort);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getListFallBackCall")
    public List<Hotel> getList() {
        return hotelServiceClient.getList();
    }

    public Hotel deleteFallBackCall(String id) {
        Hotel hotel = new Hotel();
        hotel.setName("FAILED HOTEL SERVICE CALL! - FALLING BACK" + id);
        return hotel;
    }


    public Hotel getByIdFallBackCall(String id) {
        Hotel hotel = new Hotel();
        hotel.setName("FAILED HOTEL SERVICE CALL! - FALLING BACK" + id);
        return hotel;
    }

    public Hotel saveFallBackCall(String name) {
        Hotel hotel = new Hotel();
        hotel.setName("FAILED HOTEL SERVICE CALL! - FALLING BACK" + name);
        return hotel;
    }

    public PageImplWrapper<Hotel> getPageFallBackCall(Integer page, Integer size, List<String> sort) {
        return new PageImplWrapper<Hotel>(Lists.<Hotel>newArrayList());
    }

    public List<Hotel> getListFallBackCall() {
        return Lists.newArrayList();
    }
}

