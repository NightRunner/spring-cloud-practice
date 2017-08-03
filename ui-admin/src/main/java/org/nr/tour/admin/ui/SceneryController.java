package org.nr.tour.admin.ui;

import org.nr.tour.common.util.JsonService;
import org.nr.tour.constant.PageConstants;
import org.nr.tour.domain.PageImplWrapper;
import org.nr.tour.domain.Scenery;
import org.nr.tour.rpc.hystrix.HystrixSceneryServiceClient;
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
@RequestMapping("scenery")
public class SceneryController {

    @Autowired
    HystrixSceneryServiceClient hystrixSceneryServiceClient;

    @Autowired
    JsonService jsonService;

    @RequestMapping("/list")
    public String list(Model model,
                       @RequestParam(value = "page", required = false, defaultValue = PageConstants.DEFAULT_PAGE_NUMBER) Integer page,
                       @RequestParam(value = "size", required = false, defaultValue = PageConstants.DEFAULT_PAGE_SIZE) Integer size,
                       @RequestParam(value = "sort", required = false) List<String> sort) {
        final PageImplWrapper<Scenery> pageList = hystrixSceneryServiceClient.getPage(page, size, sort);
        model.addAttribute("page", pageList);
        return "scenery/sceneryList";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Scenery save(@ModelAttribute Scenery Scenery) {
        return hystrixSceneryServiceClient.save(jsonService.toJson(Scenery));
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Scenery get(@RequestParam("id") String id) {
        return hystrixSceneryServiceClient.getById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Scenery delete(@RequestParam(value = "id") String id) {
        return hystrixSceneryServiceClient.deleteById(id);
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form(Model model, @RequestParam(value = "id", required = false) String id) {

        Scenery Scenery = null;
        if (StringUtils.isEmpty(id)) {
            Scenery = new Scenery();
        } else {
            Scenery = hystrixSceneryServiceClient.getById(id);
        }

        model.addAttribute("entity", Scenery);

        return "scenery/sceneryForm";
    }
}
