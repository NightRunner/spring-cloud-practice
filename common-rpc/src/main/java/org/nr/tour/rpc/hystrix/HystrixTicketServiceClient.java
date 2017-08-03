package org.nr.tour.rpc.hystrix;

import com.google.common.collect.Lists;
import org.nr.tour.domain.PageImplWrapper;
import org.nr.tour.domain.Ticket;
import org.nr.tour.rpc.TicketServiceClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Service
public class HystrixTicketServiceClient implements TicketServiceClient {

    @Autowired
    private TicketServiceClient ticketServiceClient;

    @Override
    @HystrixCommand(fallbackMethod = "getListBySceneryIdFallBackCall")
    public List<Ticket> getListBySceneryId(String sceneryId) {
        return ticketServiceClient.getListBySceneryId(sceneryId);
    }

    public List<Ticket> getListBySceneryIdFallBackCall(String sceneryId) {
        return null;
    }

    @Override
    @HystrixCommand(fallbackMethod = "deleteFallBackCall")
    public Boolean deleteById(String id) {
        return ticketServiceClient.deleteById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "saveFallBackCall")
    public Ticket save(String dtoJson) {
        return ticketServiceClient.save(dtoJson);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getByIdFallBackCall")
    public Ticket getById(String id) {
        return ticketServiceClient.getById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getPageFallBackCall")
    public PageImplWrapper<Ticket> getPage(Integer page, Integer size, List<String> sort) {
        return ticketServiceClient.getPage(page, size, sort);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getListFallBackCall")
    public List<Ticket> getList() {
        return ticketServiceClient.getList();
    }

    public Boolean deleteFallBackCall(String id) {
        return false;
    }

    public Ticket getByIdFallBackCall(String id) {
        return null;
    }

    public Ticket saveFallBackCall(String name) {
        return null;
    }

    public PageImplWrapper<Ticket> getPageFallBackCall(Integer page, Integer size, List<String> sort) {
        return new PageImplWrapper<Ticket>(Lists.<Ticket>newArrayList());
    }

    public List<Ticket> getListFallBackCall() {
        return Lists.<Ticket>newArrayList();
    }
}

