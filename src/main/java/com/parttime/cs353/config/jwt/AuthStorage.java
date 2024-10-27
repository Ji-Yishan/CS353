package com.parttime.cs353.config.jwt;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-28 00:04
 **/

import com.parttime.cs353.pojo.data.UserDO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.Objects;

/**
 * 存储本次请求的授权信息，适用于各种业务场景，包括分布式部署
 *
 * @author lqd
 */
public class AuthStorage {

    public static final String TOKEN_KEY = "token";

    /**
     * 模拟session
     */
    private static final HashMap<String, UserDO> JWT_USER = new HashMap<String, UserDO>();

    /**
     * 全局获取用户
     */
    public static UserDO getUser() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        return JWT_USER.get(request.getHeader(TOKEN_KEY));
    }

    /**
     * 设置用户
     */
    public static void setUser(String token, UserDO user) {
        JWT_USER.put(token, user);
    }

    /**
     * 清除授权
     */
    public static void clearUser() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        JWT_USER.remove(request.getHeader(TOKEN_KEY));
    }
}

