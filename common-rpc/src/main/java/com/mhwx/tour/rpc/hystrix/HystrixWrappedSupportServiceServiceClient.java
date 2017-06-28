package org.nr.tour.rpc.hystrix;

import com.google.common.collect.Lists;
import org.nr.tour.rpc.SupportServiceServiceClient;
import org.nr.tour.domain.PageImplWrapper;
import org.nr.tour.domain.SupportService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Service
public class HystrixWrappedSupportServiceServiceClient implements SupportServiceServiceClient {

    @Autowired
    private SupportServiceServiceClient supportServiceServiceClient;

    @Override
    @HystrixCommand(fallbackMethod = "deleteFallBackCall")
    public SupportService deleteById(String id) {
        return supportServiceServiceClient.deleteById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "saveFallBackCall")
    public SupportService save(String dtoJson) {
        return supportServiceServiceClient.save(dtoJson);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getByIdFallBackCall")
    public SupportService getById(String id) {
        return supportServiceServiceClient.getById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getPageFallBackCall")
    public PageImplWrapper<SupportService> getPage(Integer page, Integer size, List<String> sort) {
        return supportServiceServiceClient.getPage(page, size, sort);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getListFallBackCall")
    public List<SupportService> getList() {
        return supportServiceServiceClient.getList();
    }


    public SupportService deleteFallBackCall(String id) {
        SupportService supportService = new SupportService();
        supportService.setName("FAILED HOTEL SERVICE CALL! - FALLING BACK" + id);
        return supportService;
    }

    public SupportService getByIdFallBackCall(String id) {
        SupportService supportService = new SupportService();
        supportService.setName("FAILED HOTEL SERVICE CALL! - FALLING BACK" + id);
        return supportService;
    }

    public SupportService saveFallBackCall(String name) {
        SupportService supportService = new SupportService();
        supportService.setName("FAILED HOTEL SERVICE CALL! - FALLING BACK" + name);
        return supportService;
    }

    public PageImplWrapper<SupportService> getPageFallBackCall(Integer page, Integer size, List<String> sort) {
        return new PageImplWrapper<SupportService>(Lists.<SupportService>newArrayList());
    }

    public List<SupportService> getListFallBackCall() {
        return Lists.<SupportService>newArrayList();
    }
}

