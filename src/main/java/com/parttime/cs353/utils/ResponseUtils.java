package com.parttime.cs353.utils;

/**
 * @Description:响应工具类
 * @author: Isabella
 * @create: 2024-10-26 16:16
 **/


import com.parttime.cs353.exception.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;


public class ResponseUtils {
    private static final Logger logger = LoggerFactory.getLogger(ResponseUtils.class);

    /**
     * 向浏览器响应一个json字符串
     *
     * @param response 响应对象
     * @param status   状态码
     * @param msg      响应信息
     */
    public static void write(HttpServletResponse response, int status, String msg) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.setStatus(status);
            PrintWriter out = response.getWriter();
            out.write(JsonUtils.toString(new Result(status, msg, null)));
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error("响应出错：" + msg, e);
        }
    }

    public static void write(HttpServletResponse response, int status, String msg,Object object) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.setStatus(status);

            PrintWriter out = response.getWriter();
//            放入data
            out.write(JsonUtils.toString(new Result(status, msg, object)));
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error("响应出错：" + msg, e);
        }
    }
}
