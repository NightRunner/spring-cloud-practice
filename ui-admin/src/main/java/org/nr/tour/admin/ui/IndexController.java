package org.nr.tour.admin.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Controller
public class IndexController {

    @RequestMapping({"/", "index"})
    public String list(Model model) {
        return "redirect:/hotel/list";
    }

    @RequestMapping({"config"})
    public String config(Model model) {
        return "config";
    }

}
