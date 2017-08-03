package org.nr.tour.common.service;

import org.nr.tour.constant.PageConstants;
import org.nr.tour.domain.PageImplWrapper;
import org.nr.tour.domain.Scenery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public interface SceneryServiceDefinition {

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    PageImplWrapper<Scenery> getPage(
            @RequestParam(value = "page", required = false, defaultValue = PageConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(value = "size", required = false, defaultValue = PageConstants.DEFAULT_PAGE_SIZE) Integer size,
            @RequestParam(value = "sort", required = false, defaultValue = "") List<String> sort);

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    List<Scenery> getList();

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Scenery save(@RequestParam("dtoJson") String dtoJson);

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    Scenery deleteById(@RequestParam("id") String id);

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    Scenery getById(@RequestParam("id") String id);

    @RequestMapping(value = "/recommend/list", method = RequestMethod.GET)
    List<Scenery> getRecommendList();


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    PageImplWrapper<Scenery> search(@RequestParam("city") String city,
                                    @RequestParam("keyword") String keyword,
                                    @RequestParam("page") Integer page, @RequestParam("size") Integer size);

}
