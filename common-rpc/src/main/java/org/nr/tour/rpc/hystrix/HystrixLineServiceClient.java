package org.nr.tour.rpc.hystrix;

import com.google.common.collect.Lists;
import org.nr.tour.rpc.LineServiceClient;
import org.nr.tour.domain.Line;
import org.nr.tour.domain.PageImplWrapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Service
public class HystrixLineServiceClient implements LineServiceClient {

    @Autowired
    private LineServiceClient lineServiceClient;

    @Override
    @HystrixCommand(fallbackMethod = "deleteByIdFallBackCall")
    public Line deleteById(String id) {
        return lineServiceClient.deleteById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "saveFallBackCall")
    public Line save(String dtoJson) {
        return lineServiceClient.save(dtoJson);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getByIdFallBackCall")
    public Line getById(String id) {
        return lineServiceClient.getById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getPageFallBackCall")
    public PageImplWrapper<Line> getPage(Integer page, Integer size, List<String> sort) {
        return lineServiceClient.getPage(page, size, sort);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getListFallBackCall")
    public List<Line> getList() {
        return lineServiceClient.getList();
    }

    public Line deleteByIdFallBackCall(String id) {
        Line line = new Line();
        line.setName("FAILED HOTEL SERVICE CALL! - FALLING BACK" + id);
        return line;
    }

    public Line getByIdFallBackCall(String id) {
        Line line = new Line();
        line.setName("FAILED HOTEL SERVICE CALL! - FALLING BACK" + id);
        return line;
    }

    public Line saveFallBackCall(String name) {
        Line line = new Line();
        line.setName("FAILED HOTEL SERVICE CALL! - FALLING BACK" + name);
        return line;
    }

    public PageImplWrapper<Line> getPageFallBackCall(Integer page, Integer size, List<String> sort) {
        return new PageImplWrapper<Line>(Lists.<Line>newArrayList());
    }

    public List<Line> getListFallBackCall() {
        return Lists.<Line>newArrayList();
    }
}

