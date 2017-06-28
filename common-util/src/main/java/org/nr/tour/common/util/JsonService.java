package org.nr.tour.common.util;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public interface JsonService {
    String toJson(Object object);

    Object parseObject(String jsonString);

    <T> T parseObject(String jsonString, Class<T> clazz);

    <T> List<T> parseArray(String jsonString, Class<T> clazz);
}
