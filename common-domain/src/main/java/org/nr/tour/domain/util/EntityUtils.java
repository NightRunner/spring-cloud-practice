package org.nr.tour.domain.util;

import org.nr.tour.domain.IDEntity;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public class EntityUtils {

    public static void preSave(IDEntity entity) {
        if (StringUtils.isEmpty(entity.getId())) {
            entity.setId(UUID.randomUUID().toString().replace("-", ""));
        }
    }
}
