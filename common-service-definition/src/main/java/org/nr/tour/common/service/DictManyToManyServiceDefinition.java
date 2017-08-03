package org.nr.tour.common.service;

import org.nr.tour.domain.DictManyToMany;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public interface DictManyToManyServiceDefinition {

    @RequestMapping(value = "findBySourceIdAndDictType")
    List<DictManyToMany> findBySourceIdAndDictType(@RequestParam("sourceId") String sourceId,
                                                   @RequestParam("dictType") String dictType);

    @RequestMapping(value = "saveBySourceIdAndDictTypeAndDictIds")
    void saveBySourceIdAndDictTypeAndDictIds(@RequestParam("sourceId") String sourceId,
                                             @RequestParam("dictType") String dictType,
                                             @RequestParam("dictIds") List<String> dictIds);

}
