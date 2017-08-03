package org.nr.tour.rpc.hystrix;

import com.google.common.collect.Lists;
import org.nr.tour.domain.Dict;
import org.nr.tour.domain.PageImplWrapper;
import org.nr.tour.rpc.DictServiceClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Service
public class HystrixDictServiceClient implements DictServiceClient {

    @Autowired
    private DictServiceClient dictServiceClient;

    @Override
    @HystrixCommand(fallbackMethod = "findByTypeFallBackCall")
    public List<Dict> findByType(String type) {
        return dictServiceClient.findByType(type);
    }

    public List<Dict> findByTypeFallBackCall(String type) {
        return Lists.newArrayList();
    }


    @Override
    @HystrixCommand(fallbackMethod = "deleteFallBackCall")
    public Dict deleteById(String id) {
        return dictServiceClient.deleteById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "saveFallBackCall")
    public Dict save(String dtoJson) {
        return dictServiceClient.save(dtoJson);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getByIdFallBackCall")
    public Dict getById(String id) {
        return dictServiceClient.getById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getPageFallBackCall")
    public PageImplWrapper<Dict> getPage(Integer page, Integer size, List<String> sort) {
        return dictServiceClient.getPage(page, size, sort);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getListFallBackCall")
    public List<Dict> getList() {
        return dictServiceClient.getList();
    }

    public Dict deleteFallBackCall(String id) {
        return null;
    }

    public Dict getByIdFallBackCall(String id) {
        return null;
    }

    public Dict saveFallBackCall(String name) {
        return null;
    }

    public PageImplWrapper<Dict> getPageFallBackCall(Integer page, Integer size, List<String> sort) {
        return null;
    }

    public List<Dict> getListFallBackCall() {
        return null;
    }
}

