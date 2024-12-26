package com.parttime.cs353.config.jwt;


import com.parttime.cs353.config.jwt.Payload;
import com.parttime.cs353.config.jwt.SecurityUser;
import com.parttime.cs353.pojo.data.UserDO;
import com.parttime.cs353.utils.JsonUtils;
import com.parttime.cs353.utils.JwtUtils;
import com.parttime.cs353.utils.ResponseUtils;
import com.parttime.cs353.utils.RsaUtils;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.PublicKey;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-30 00:01
 **/
@Slf4j
public class TokenFilter implements Filter {
    public TokenFilter() throws Exception {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info(filterConfig.getFilterName() + " init");
    }
    private static final Set<String> WHITE_LIST = Stream.of(
            "/register",
            "/login",
            "/other/login",
            "/company/register",
            "/search/Job",
            "/search/Company",
            "/search/filter",
            "/hello"
    ).collect(Collectors.toSet());
    private static final Set<String> COMPANY = Stream.of(
            "/jobG",
            "/resumepage/company/jobD",
            "/hello"
    ).collect(Collectors.toSet());
    private static final Set<String> TYPES = Stream.of(
            "user", "hr","admin","company"
    ).collect(Collectors.toSet());
    private final PublicKey publick= RsaUtils.getPublicKey("C:\\auth_key\\rsa_key.pub");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        log.info("token filter begin");
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            log.info("start the internal process");

            HttpServletRequest req=(HttpServletRequest) request;

//            @Override
//            public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
            try {
                log.info("running JwtVerificationFilter");
                String header = req.getHeader("Authorization");
                // white list
                log.info(req.getServletPath());
                log.info("whether this is in the white list："+String.valueOf(WHITE_LIST.contains(req.getServletPath())));
                if (WHITE_LIST.contains(req.getServletPath())) {
                    chain.doFilter(request, response);
                    return;
                }

                log.info(header);
                if (header == null || !header.startsWith("Bearer ")) {
                    //if token in wrong form, inform user invalid login
//                    chain.doFilter(request, response);
                    ResponseUtils.write(res, HttpServletResponse.SC_FORBIDDEN, "invalid login");
                    return;
                } else {
                    //if token in correct form, get the token
                    String token = header.replace("Bearer ", "");
                    //use public key to unwrap the token to verify
                    Payload<SecurityUser> payload = JwtUtils.getInfoFromToken(token, publick);
//                    SecurityUser sysUser = payload.getUserInfo();
                    if(COMPANY.contains(req.getServletPath())){
                        if(!JwtUtils.getTokenBody(token,publick).get("type").equals("company")){
                            ResponseUtils.write(res, HttpServletResponse.SC_FORBIDDEN, "user not authorized");
                            log.info("not company, unauthorized");
                            return;
                        }
                        log.info("company with authorization");
                        chain.doFilter(request, response);
                        return;
                    }
                    if (payload.getId() != null) {
//                        UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(sysUser.getUsername(), null, sysUser.getAuthorities());
//                        SecurityContextHolder.getContext().setAuthentication(authResult);
                        if(!TYPES.contains(JwtUtils.getTokenBody(token,publick).get("type"))){
                            log.info("type error");
                            ResponseUtils.write(res,HttpServletResponse.SC_FORBIDDEN,"user type error");
                            return;
                        }
                        log.info("valid token");
                        chain.doFilter(request, response);
                        return;

                    } else {
                        ResponseUtils.write(res, HttpServletResponse.SC_FORBIDDEN, "user verification fail");
                        return;
                    }
                }
            } catch (ExpiredJwtException e) {
                ResponseUtils.write(res, HttpServletResponse.SC_FORBIDDEN, "please login again");
                return;
            }
//            }

//            res.setHeader("Authorization", "Bearer " + "sha");
//            ResponseUtils.write(res, HttpServletResponse.SC_OK, "用户认证通过！");
//            chain.doFilter(request, response);
        } catch (Exception e) {
            log.error("error in token filter!", e);
            ResponseUtils.write(res, HttpServletResponse.SC_FORBIDDEN, "please login again");
        }
        log.info("token filter end");
    }

    @Override
    public void destroy() {
    }
}

