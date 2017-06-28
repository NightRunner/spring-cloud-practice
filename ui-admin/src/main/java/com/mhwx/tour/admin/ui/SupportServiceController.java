package org.nr.tour.admin.ui;

import com.google.common.collect.Lists;
import org.nr.tour.rpc.hystrix.HystrixWrappedSupportServiceCategoryServiceClient;
import org.nr.tour.rpc.hystrix.HystrixWrappedSupportServiceServiceClient;
import org.nr.tour.common.util.JsonService;
import org.nr.tour.constant.PageConstants;
import org.nr.tour.domain.PageImplWrapper;
import org.nr.tour.domain.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Controller
@RequestMapping("/hotel/service")
public class SupportServiceController {

    @Autowired
    HystrixWrappedSupportServiceServiceClient hystrixWrappedSupportServiceServiceClient;

    @Autowired
    HystrixWrappedSupportServiceCategoryServiceClient hystrixWrappedSupportServiceCategoryServiceClient;

    @Autowired
    JsonService jsonService;

    @RequestMapping("/list")
    public String list(Model model,
                       @RequestParam(value = "page", required = false, defaultValue = PageConstants.DEFAULT_PAGE_NUMBER) Integer page,
                       @RequestParam(value = "size", required = false, defaultValue = PageConstants.DEFAULT_PAGE_SIZE) Integer size,
                       @RequestParam(value = "sort", required = false) List<String> sort) {
        final PageImplWrapper<SupportService> pageList = hystrixWrappedSupportServiceServiceClient.getPage(page, size, sort);
        model.addAttribute("page", pageList);
        return "hotel/service/supportServiceList";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public SupportService save(@ModelAttribute SupportService SupportService) {
        return hystrixWrappedSupportServiceServiceClient.save(jsonService.toJson(SupportService));
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public SupportService get(@RequestParam("id") String id) {
        return hystrixWrappedSupportServiceServiceClient.getById(id);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<SupportService> getAll() {
        return hystrixWrappedSupportServiceServiceClient.getPage(0, Integer.MAX_VALUE, Lists.newArrayList("sort,asc")).getContent();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public SupportService delete(@RequestParam(value = "id") String id) {
        return hystrixWrappedSupportServiceServiceClient.deleteById(id);
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form(Model model, @RequestParam(value = "id", required = false) String id) {

        SupportService supportService = null;
        if (StringUtils.isEmpty(id)) {
            supportService = new SupportService();
        } else {
            supportService = hystrixWrappedSupportServiceServiceClient.getById(id);
        }

        model.addAttribute("categories",
                hystrixWrappedSupportServiceCategoryServiceClient.getPage(0, Integer.MAX_VALUE, null).getContent());
        model.addAttribute("entity", supportService);

        return "hotel/service/supportServiceForm";
    }
}
