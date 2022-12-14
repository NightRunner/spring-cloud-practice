package org.nr.tour.common.service;

import org.nr.tour.constant.PageConstants;
import org.nr.tour.domain.IDEntity;
import org.nr.tour.domain.PageImplWrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public interface AbstractServiceDefinition<T extends IDEntity, ID extends String> {
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    PageImplWrapper<T> getPage(
            @RequestParam(value = "page", required = false, defaultValue = PageConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(value = "size", required = false, defaultValue = PageConstants.DEFAULT_PAGE_SIZE) Integer size,
            @RequestParam(value = "sort", required = false, defaultValue = "") List<String> sort);

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    List<T> getList();

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    T save(@RequestParam("dtoJson") String dtoJson);

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    T deleteById(@RequestParam("id") ID id);

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    T getById(@RequestParam("id") ID id);
}
