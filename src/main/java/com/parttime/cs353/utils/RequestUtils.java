package com.parttime.cs353.utils;

/**
 * @Description:请求工具类
 * @author: Isabella
 * @create: 2024-10-26 16:15
 **/

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestUtils {
    private static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);

    /**
     * 从请求对象的输入流中获取指定类型对象
     *
     * @param request 请求对象
     * @param clazz   指定类型
     * @return 指定类型对象
     */
    public static <T> T read(HttpServletRequest request, Class<T> clazz) {
        try {
            return JsonUtils.toBean(request.getInputStream(), clazz);
        } catch (Exception e) {
            logger.error("读取出错：" + clazz, e);
            return null;
        }
    }
}
