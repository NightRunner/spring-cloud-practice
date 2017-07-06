package org.nr.tour.rpc.hystrix;

import com.google.common.collect.Lists;
import org.nr.tour.rpc.SceneryServiceClient;
import org.nr.tour.domain.PageImplWrapper;
import org.nr.tour.domain.Scenery;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Service
public class HystrixSceneryServiceClient implements SceneryServiceClient {

    @Autowired
    private SceneryServiceClient sceneryServiceClient;

    @Override
    @HystrixCommand(fallbackMethod = "deleteFallBackCall")
    public Scenery deleteById(String id) {
        return sceneryServiceClient.deleteById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "saveFallBackCall")
    public Scenery save(String dtoJson) {
        return sceneryServiceClient.save(dtoJson);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getByIdFallBackCall")
    public Scenery getById(String id) {
        return sceneryServiceClient.getById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getPageFallBackCall")
    public PageImplWrapper<Scenery> getPage(Integer page, Integer size, List<String> sort) {
        return sceneryServiceClient.getPage(page, size,sort);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getListFallBackCall")
    public List<Scenery> getList() {
        return sceneryServiceClient.getList();
    }

    public Scenery deleteFallBackCall(String id) {
        Scenery scenery = new Scenery();
        scenery.setName("FAILED HOTEL SERVICE CALL! - FALLING BACK" + id);
        return scenery;
    }

    public Scenery getByIdFallBackCall(String id) {
        Scenery scenery = new Scenery();
        scenery.setName("FAILED HOTEL SERVICE CALL! - FALLING BACK" + id);
        return scenery;
    }

    public Scenery saveFallBackCall(String name) {
        Scenery scenery = new Scenery();
        scenery.setName("FAILED HOTEL SERVICE CALL! - FALLING BACK" + name);
        return scenery;
    }

    public PageImplWrapper<Scenery> getPageFallBackCall(Integer page, Integer size, List<String> sort) {
        return new PageImplWrapper<Scenery>(Lists.<Scenery>newArrayList());
    }

    public List<Scenery> getListFallBackCall() {
        return Lists.<Scenery>newArrayList();
    }
}

