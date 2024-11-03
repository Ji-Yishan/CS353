package com.parttime.cs353.config;

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
            log.info("业务方法执行");

            HttpServletRequest req=(HttpServletRequest) request;

//            @Override
//            public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
            try {
                log.info("running JwtVerificationFilter");
                String header = req.getHeader("Authorization");
                // 白名单
                log.info(req.getServletPath());
                log.info(String.valueOf(WHITE_LIST.contains(req.getServletPath())));
                if (WHITE_LIST.contains(req.getServletPath())) {
                    chain.doFilter(request, response);
                    return;
                }
                log.info(header);
                if (header == null || !header.startsWith("Bearer ")) {
                    //如果token的格式错误，则提示用户非法登录
//                    chain.doFilter(request, response);
                    ResponseUtils.write(res, HttpServletResponse.SC_FORBIDDEN, "用户非法登录！");
                } else {
                    //如果token的格式正确，则先要获取到token
                    String token = header.replace("Bearer ", "");
                    //使用公钥进行解密然后来验证token是否正确
                    Payload<SecurityUser> payload = JwtUtils.getInfoFromToken(token, publick);
//                    SecurityUser sysUser = payload.getUserInfo();
                    if (payload.getId() != null&& payload.getUserInfo().getStatus()==0) {
//                        UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(sysUser.getUsername(), null, sysUser.getAuthorities());
//                        SecurityContextHolder.getContext().setAuthentication(authResult);
                        if(!TYPES.contains(JwtUtils.getTokenBody(token,publick).get("type"))){
                            ResponseUtils.write(res,HttpServletResponse.SC_FORBIDDEN,"用户类型错误");
                        }
                        ResponseUtils.write(res,200,"成功登录");
                    } else {
                        ResponseUtils.write(res, HttpServletResponse.SC_FORBIDDEN, "用户验证失败！");
                    }
                }
            } catch (ExpiredJwtException e) {
                ResponseUtils.write(res, HttpServletResponse.SC_FORBIDDEN, "请您重新登录！");
            }
//            }

//            res.setHeader("Authorization", "Bearer " + "sha");
//            ResponseUtils.write(res, HttpServletResponse.SC_OK, "用户认证通过！");
//            chain.doFilter(request, response);
        } catch (Exception e) {
            log.error("error in token filter!", e);
            ResponseUtils.write(res, HttpServletResponse.SC_FORBIDDEN, "请您重新登录！");
        }
        log.info("token filter end");
    }

    @Override
    public void destroy() {
    }
}

