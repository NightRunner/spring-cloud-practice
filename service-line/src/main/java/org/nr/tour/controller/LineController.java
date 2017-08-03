package org.nr.tour.controller;

import org.nr.tour.common.controller.BaseCRUDController;
import org.nr.tour.common.service.LineServiceDefinition;
import org.nr.tour.constant.PageConstants;
import org.nr.tour.domain.Line;
import org.nr.tour.domain.PageImplWrapper;
import org.nr.tour.repository.LineRepository;
import org.nr.tour.service.LineService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@RefreshScope
@Api
@RestController
public class LineController extends BaseCRUDController<Line, String> implements LineServiceDefinition {

    @Autowired
    LineRepository lineRepository;

    @Autowired
    LineService lineService;

    @Override
    protected JpaRepository<Line, String> getRepository() {
        return lineRepository;
    }

    @Override
    public PageImplWrapper<Line> getPage(
            @RequestParam(value = "page", required = false, defaultValue = PageConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(value = "size", required = false, defaultValue = PageConstants.DEFAULT_PAGE_SIZE) Integer size,
            @RequestParam(value = "sort", required = false, defaultValue = "") List<String> sorts) {
        return super.baseGetPage(page, size, sorts);
    }

    @Override
    public PageImplWrapper<Line> search(
            @RequestParam("city") String city, @RequestParam("keyword") String keyword,
            @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return new PageImplWrapper<Line>(lineService.findPageByNameLike(keyword, page, size));
    }

    @Override
    public List<Line> getList() {
        return super.baseGetList();
    }

    @Override
    public Boolean deleteById(@RequestParam("id") String s) {
        super.baseDeleteById(s);
        return Boolean.TRUE;
    }

    @Override
    public Line getById(@RequestParam("id") String s) {
        return super.baseGetById(s);
    }

    @Override
    public Line save(@RequestParam("dtoJson") String dtoJson) {
        return super.baseSave(dtoJson);
    }

    @Override
    public List<Line> getRecommendList() {
        return lineRepository.findByRecommend(Boolean.TRUE);
    }
}
