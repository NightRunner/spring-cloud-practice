package org.nr.tour.rpc;

import org.nr.tour.common.service.AbstractServiceDefinition;
import org.nr.tour.constant.ServiceConstants;
import org.nr.tour.domain.Dict;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@FeignClient(value = ServiceConstants.DICT_SERVICE)
public interface DictServiceClient extends AbstractServiceDefinition<Dict, String> {

    @RequestMapping(value = "/findByType", method = RequestMethod.GET)
    List<Dict> findByType(@RequestParam("type") String type);

}
