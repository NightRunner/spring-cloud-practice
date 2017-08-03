package org.nr.tour.common.util;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Service
public class IdServiceUUIDImpl implements IdService {

    @Override
    public String newOne() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
