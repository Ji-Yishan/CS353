package com.parttime.cs353.config.jwt;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-28 00:04
 **/

import com.parttime.cs353.pojo.data.UserDO;
import com.parttime.cs353.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;


/**
 * 拦截器
 *
 * @author lqd
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(AuthStorage.TOKEN_KEY);
        if (StringUtils.hasLength(token)) {
            UserDO jwtUser = JwtUtils.checkToken(token);
            // 是否认证通过
            if (jwtUser.isValid()) {
                // 保存授权信息
                AuthStorage.setUser(token, jwtUser);
                return true;
            }
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("请先登录！");
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object      handler, Exception ex) throws Exception {
        // 请求完成清除授权信息
        AuthStorage.clearUser();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

