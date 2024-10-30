package com.parttime.cs353.config.jwt;

import com.parttime.cs353.pojo.data.UserDO;
import com.parttime.cs353.utils.JwtUtils;
import com.parttime.cs353.utils.ResponseUtils;
import com.parttime.cs353.utils.RsaKeyProperties;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:验证过滤器
 * @author: Isabella
 * @create: 2024-10-28 21:34
 **/
//@Slf4j
//public class JwtVerificationFilter extends OncePerRequestFilter {
//    private RsaKeyProperties prop;
//
//    public JwtVerificationFilter(RsaKeyProperties prop) {
//        super();
//        this.prop = prop;
//    }
//    private static final Set<String> WHITE_LIST = Stream.of(
//            "/register",
//            "/login"
//    ).collect(Collectors.toSet());
//
//
//    @Override
//    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        try {
//            log.info("running JwtVerificationFilter");
//            String header = request.getHeader("Authorization");
//            // 白名单
//            log.info(request.getServletPath());
//            if (WHITE_LIST.contains(request.getServletPath())) {
//
//                chain.doFilter(request, response);
//                return;
//            }
//
//            if (header == null || !header.startsWith("Bearer ")) {
//                //如果token的格式错误，则提示用户非法登录
//                chain.doFilter(request, response);
//                ResponseUtils.write(response, HttpServletResponse.SC_FORBIDDEN, "用户非法登录！");
//            } else {
//                //如果token的格式正确，则先要获取到token
//                String token = header.replace("Bearer ", "");
//                //使用公钥进行解密然后来验证token是否正确
//                Payload<SecurityUser> payload = JwtUtils.getInfoFromToken(token, prop.getPublicKey());
//                SecurityUser sysUser = payload.getUserInfo();
//                if (sysUser != null) {
//                    UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(sysUser.getUsername(), null, sysUser.getAuthorities());
//                    SecurityContextHolder.getContext().setAuthentication(authResult);
//                    chain.doFilter(request, response);
//                } else {
//                    ResponseUtils.write(response, HttpServletResponse.SC_FORBIDDEN, "用户验证失败！");
//                }
//            }
//        } catch (ExpiredJwtException e) {
//            ResponseUtils.write(response, HttpServletResponse.SC_FORBIDDEN, "请您重新登录！");
//        }
//    }
//}