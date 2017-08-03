package org.nr.tour.controller;

import org.nr.tour.common.service.TicketServiceDefinition;
import org.nr.tour.common.util.JsonService;
import org.nr.tour.constant.PageConstants;
import org.nr.tour.constant.ServiceConstants;
import org.nr.tour.domain.PageImplWrapper;
import org.nr.tour.domain.Ticket;
import org.nr.tour.service.TicketService;
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
@RequestMapping(ServiceConstants.SCENERY_SERVICE_PATH_TICKET)
public class TicketController implements TicketServiceDefinition {

    @Autowired
    TicketService ticketService;

    @Autowired
    JsonService jsonService;

    @Override
    public List<Ticket> getListBySceneryId(@RequestParam("sceneryId") String sceneryId) {
        return ticketService.getBySceneryId(sceneryId);
    }

    @Override
    public PageImplWrapper<Ticket> getPage(
            @RequestParam(value = "page", required = false, defaultValue = PageConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(value = "size", required = false, defaultValue = PageConstants.DEFAULT_PAGE_SIZE) Integer size,
            @RequestParam(value = "sort", required = false, defaultValue = "") List<String> sorts) {
        return new PageImplWrapper<Ticket>(ticketService.getPage(page, size, sorts));
    }

    @Override
    public List<Ticket> getList() {
        return ticketService.getList();
    }

    @Override
    public Boolean deleteById(@RequestParam("id") String id) {
        ticketService.delete(id);
        return Boolean.TRUE;
    }

    @Override
    public Ticket getById(@RequestParam("id") String id) {
        return ticketService.getById(id);
    }

    @Override
    public Ticket save(@RequestParam("dtoJson") String dtoJson) {
        Ticket ticket = jsonService.parseObject(dtoJson, Ticket.class);
        return ticketService.save(ticket);
    }

}

