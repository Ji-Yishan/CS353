package com.parttime.cs353.utils;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-26 16:15
 **/

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestUtils {
    private static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);


    public static <T> T read(HttpServletRequest request, Class<T> clazz) {
        try {
            return JsonUtils.toBean(request.getInputStream(), clazz);
        } catch (Exception e) {
            logger.error("error reading:" + clazz, e);
            return null;
        }
    }
}
