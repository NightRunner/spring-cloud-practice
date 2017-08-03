package org.nr.tour.api.controller;

import org.nr.tour.api.Constants;
import org.nr.tour.api.service.VoBuilderService;
import org.nr.tour.api.support.APIResult;
import org.nr.tour.api.vo.LineDetailVO;
import org.nr.tour.api.vo.LineItemVO;
import org.nr.tour.api.vo.LineSearchItemVO;
import org.nr.tour.domain.Line;
import org.nr.tour.domain.PageImplWrapper;
import org.nr.tour.rpc.hystrix.HystrixLineServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@RestController
public class LineEndpoint {

    @Autowired
    HystrixLineServiceClient hystrixLineServiceClient;

    @Autowired
    VoBuilderService voBuilderService;

    @RequestMapping("line/detail")
    public Object lineDetail(@RequestParam String id) {
        Line line = hystrixLineServiceClient.getById(id);
        LineDetailVO vo = voBuilderService.buildDetailVO(line);
        return APIResult.createSuccess(vo);
    }

    @RequestMapping("line/recommend/list")
    public Object lineRecommendList() {
        final List<Line> recommendList = hystrixLineServiceClient.getRecommendList();

        List<LineItemVO> lineVOList = new ArrayList<LineItemVO>();
        for (Line line : recommendList) {
            lineVOList.add(voBuilderService.buildItemVO(line));
        }

        return APIResult.createSuccess(lineVOList);
    }

    @RequestMapping("line/search")
    public Object lineSearchList(@RequestParam String city,
                                 @RequestParam(required = false, defaultValue = "") String keyword,
                                 @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_NO) Integer pageNo,
                                 @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE) Integer pageSize) {
        PageImplWrapper<Line> pageImplWrapper = hystrixLineServiceClient.search(city,
                StringUtils.isEmpty(keyword) ? "" : keyword, pageNo, pageSize);
        List<LineSearchItemVO> voList = new ArrayList<LineSearchItemVO>();

        for (Line line : pageImplWrapper.getContent()) {
            voList.add(voBuilderService.buildSearchItemVO(line));
        }

        return APIResult.createSuccess(voBuilderService.buildPage(voList, !pageImplWrapper.isLast()));
    }

}
