package com.parttime.cs353.config.jwt;

/**
 * @Description:认证过滤器
 * @author: Isabella
 * @create: 2024-10-26 16:38
 **/

import com.parttime.cs353.pojo.data.UserDO;
import com.parttime.cs353.pojo.dto.UserLoginDTO;
import com.parttime.cs353.utils.JwtUtils;
import com.parttime.cs353.utils.RequestUtils;
import com.parttime.cs353.utils.ResponseUtils;
import com.parttime.cs353.utils.RsaKeyProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;


@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private RsaKeyProperties prop;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, RsaKeyProperties prop) {
        this.authenticationManager = authenticationManager;
        this.prop = prop;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("running jwt attemptAuthentication");
        UserLoginDTO user = RequestUtils.read(request, UserLoginDTO.class);
        String username = user.getPhone();
        username = username != null ? username : "";
        String password = user.getPassword();
        password = password != null ? password : "";

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authRequest);
    }

    /**
     * 认证成功所执行的方法
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDO sysUser = new UserDO();
        sysUser.setName(authResult.getName());
        sysUser.setType(new ArrayList(authResult.getAuthorities()).toString());
        String token = JwtUtils.generateTokenExpireInMinutes(sysUser, prop.getPrivateKey(), 24 * 60);
        response.addHeader("Authorization", "Bearer " + token);
        ResponseUtils.write(response, HttpServletResponse.SC_OK, "用户认证通过！");
    }

    /**
     * 认证失败所执行的方法
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        //清理上下文
        SecurityContextHolder.clearContext();
        System.out.println(failed);
        //判断异常类
        if (failed instanceof InternalAuthenticationServiceException) {
            ResponseUtils.write(response, HttpServletResponse.SC_FORBIDDEN, "认证服务不正常！");
        } else if (failed instanceof UsernameNotFoundException) {
            ResponseUtils.write(response, HttpServletResponse.SC_FORBIDDEN, "用户账户不存在！");
        } else if (failed instanceof BadCredentialsException) {
            ResponseUtils.write(response, HttpServletResponse.SC_FORBIDDEN, "用户密码是错的！");
        } else if (failed instanceof AccountExpiredException) {
            ResponseUtils.write(response, HttpServletResponse.SC_FORBIDDEN, "用户账户已过期！");
        } else if (failed instanceof LockedException) {
            ResponseUtils.write(response, HttpServletResponse.SC_FORBIDDEN, "用户账户已被锁！");
        } else if (failed instanceof CredentialsExpiredException) {
            ResponseUtils.write(response, HttpServletResponse.SC_FORBIDDEN, "用户密码已失效！");
        } else if (failed instanceof DisabledException) {
            ResponseUtils.write(response, HttpServletResponse.SC_FORBIDDEN, "用户账户已被锁！");
        }
    }
}