package org.nr.tour.api.controller;

import org.nr.tour.api.Constants;
import org.nr.tour.api.service.VoBuilderService;
import org.nr.tour.api.support.APIResult;
import org.nr.tour.api.vo.SceneryItemVO;
import org.nr.tour.domain.PageImplWrapper;
import org.nr.tour.domain.Scenery;
import org.nr.tour.rpc.hystrix.HystrixSceneryServiceClient;
import org.nr.tour.rpc.hystrix.HystrixTicketServiceClient;
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
public class SceneryAndTicketEndpoint {

    @Autowired
    HystrixSceneryServiceClient hystrixSceneryServiceClient;
    @Autowired
    HystrixTicketServiceClient hystrixTicketServiceClient;

    @Autowired
    VoBuilderService voBuilderService;

    @RequestMapping("scenery/detail")
    public Object sceneryDetail(@RequestParam String id) {
        Scenery scenery = hystrixSceneryServiceClient.getById(id);
        return APIResult.createSuccess(voBuilderService.buildDetailVO(scenery));
    }

    @RequestMapping("scenery/recommend/list")
    public Object sceneryRecommendList() {
        final List<Scenery> recommendList = hystrixSceneryServiceClient.getRecommendList();

        List<SceneryItemVO> sceneryVOList = new ArrayList<SceneryItemVO>();
        for (Scenery scenery : recommendList) {
            sceneryVOList.add(voBuilderService.buildItemVO(scenery));
        }

        return APIResult.createSuccess(sceneryVOList);
    }

    @RequestMapping("scenery/search")
    public Object scenerySearchList(@RequestParam String city,
                                    @RequestParam(required = false, defaultValue = "") String keyword,
                                    @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_NO) Integer pageNo,
                                    @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE) Integer pageSize) {
        PageImplWrapper<Scenery> pageImplWrapper = hystrixSceneryServiceClient.search(city,
                StringUtils.isEmpty(keyword) ? "" : keyword, pageNo, pageSize);
        List<SceneryItemVO> voList = new ArrayList<SceneryItemVO>();

        for (Scenery scenery : pageImplWrapper.getContent()) {
            voList.add(voBuilderService.buildItemVO(scenery));
        }

        return APIResult.createSuccess(voBuilderService.buildPage(voList, !pageImplWrapper.isLast()));
    }

}
