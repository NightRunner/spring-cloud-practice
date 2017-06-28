package org.nr.tour.admin.ui;

import org.nr.tour.common.util.JsonService;
import org.nr.tour.constant.PageConstants;
import org.nr.tour.domain.Member;
import org.nr.tour.domain.PageImplWrapper;
import org.nr.tour.rpc.hystrix.HystrixWrappedMemberServiceClient;
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
@RequestMapping("member")
public class MemberController {

    @Autowired
    HystrixWrappedMemberServiceClient hystrixWrappedMemberServiceClient;

    @Autowired
    JsonService jsonService;

    @RequestMapping("/list")
    public String list(Model model,
                       @RequestParam(value = "page", required = false, defaultValue = PageConstants.DEFAULT_PAGE_NUMBER) Integer page,
                       @RequestParam(value = "size", required = false, defaultValue = PageConstants.DEFAULT_PAGE_SIZE) Integer size,
                       @RequestParam(value = "sort", required = false) List<String> sort) {
        final PageImplWrapper<Member> pageList = hystrixWrappedMemberServiceClient.getPage(page, size, sort);
        model.addAttribute("page", pageList);
        return "member/memberList";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Member save(@ModelAttribute Member member) {
        return hystrixWrappedMemberServiceClient.save(jsonService.toJson(member));
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Member get(@RequestParam("id") String id) {
        return hystrixWrappedMemberServiceClient.getById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Member delete(@RequestParam(value = "id") String id) {
        return hystrixWrappedMemberServiceClient.deleteById(id);
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form(Model model, @RequestParam(value = "id", required = false) String id) {

        Member Member = null;
        if (StringUtils.isEmpty(id)) {
            Member = new Member();
        } else {
            Member = hystrixWrappedMemberServiceClient.getById(id);
        }

        model.addAttribute("entity", Member);

        return "member/memberForm";
    }
}
