package org.nr.tour.controller;

import org.nr.tour.common.service.RoomServiceDefinition;
import org.nr.tour.common.util.JsonService;
import org.nr.tour.constant.PageConstants;
import org.nr.tour.constant.ServiceConstants;
import org.nr.tour.domain.PageImplWrapper;
import org.nr.tour.domain.Room;
import org.nr.tour.service.RoomService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@RefreshScope
@Api
@RestController
@RequestMapping(ServiceConstants.HOTEL_SERVICE_PATH_ROOM)
public class RoomController implements RoomServiceDefinition {

    @Autowired
    RoomService ticketService;

    @Autowired
    JsonService jsonService;

    @Override
    public List<Room> getByHotelId(@RequestParam("hotelId") String hotelId) {
        return ticketService.getByHotelId(hotelId);
    }

    @Override
    public PageImplWrapper<Room> getPage(
            @RequestParam(value = "page", required = false, defaultValue = PageConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(value = "size", required = false, defaultValue = PageConstants.DEFAULT_PAGE_SIZE) Integer size,
            @RequestParam(value = "sort", required = false, defaultValue = "") List<String> sorts) {
        return new PageImplWrapper<Room>(ticketService.getPage(page, size, sorts));
    }

    @Override
    public List<Room> getList() {
        return ticketService.getList();
    }

    @Override
    public Boolean deleteById(@RequestParam("id") String id) {
        ticketService.delete(id);
        return Boolean.TRUE;
    }

    @Override
    public Room getById(@RequestParam("id") String id) {
        return ticketService.getById(id);
    }

    @Override
    public Room save(@RequestParam("dtoJson") String dtoJson) {
        Room ticket = jsonService.parseObject(dtoJson, Room.class);
        return ticketService.save(ticket);
    }

}

