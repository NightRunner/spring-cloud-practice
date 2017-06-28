package org.nr.tour.rpc.hystrix;

import com.google.common.collect.Lists;
import org.nr.tour.rpc.SupportServiceCategoryServiceClient;
import org.nr.tour.domain.PageImplWrapper;
import org.nr.tour.domain.SupportServiceCategory;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Service
public class HystrixWrappedSupportServiceCategoryServiceClient implements SupportServiceCategoryServiceClient {

    @Autowired
    private SupportServiceCategoryServiceClient supportServiceCategoryServiceClient;

    @Override
    @HystrixCommand(fallbackMethod = "deleteFallBackCall")
    public SupportServiceCategory deleteById(String id) {
        return supportServiceCategoryServiceClient.deleteById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "saveFallBackCall")
    public SupportServiceCategory save(String dtoJson) {
        return supportServiceCategoryServiceClient.save(dtoJson);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getByIdFallBackCall")
    public SupportServiceCategory getById(String id) {
        return supportServiceCategoryServiceClient.getById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getPageFallBackCall")
    public PageImplWrapper<SupportServiceCategory> getPage(Integer page, Integer size, List<String> sort) {
        return supportServiceCategoryServiceClient.getPage(page, size, sort);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getListFallBackCall")
    public List<SupportServiceCategory> getList() {
        return supportServiceCategoryServiceClient.getList();
    }

    public SupportServiceCategory deleteFallBackCall(String id) {
        SupportServiceCategory supportServiceCategory = new SupportServiceCategory();
        supportServiceCategory.setName("FAILED HOTEL SERVICE CALL! - FALLING BACK" + id);
        return supportServiceCategory;
    }

    public SupportServiceCategory getByIdFallBackCall(String id) {
        SupportServiceCategory supportServiceCategory = new SupportServiceCategory();
        supportServiceCategory.setName("FAILED HOTEL SERVICE CALL! - FALLING BACK" + id);
        return supportServiceCategory;
    }

    public SupportServiceCategory saveFallBackCall(String name) {
        SupportServiceCategory supportServiceCategory = new SupportServiceCategory();
        supportServiceCategory.setName("FAILED HOTEL SERVICE CALL! - FALLING BACK" + name);
        return supportServiceCategory;
    }

    public PageImplWrapper<SupportServiceCategory> getPageFallBackCall(Integer page, Integer size, List<String> sort) {
        return new PageImplWrapper<SupportServiceCategory>(Lists.<SupportServiceCategory>newArrayList());
    }

    public List<SupportServiceCategory> getListFallBackCall() {
        return Lists.<SupportServiceCategory>newArrayList();
    }
}

