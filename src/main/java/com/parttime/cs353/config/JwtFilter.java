package com.parttime.cs353.config;

import com.parttime.cs353.config.jwt.Payload;
import com.parttime.cs353.config.jwt.SecurityUser;
import com.parttime.cs353.pojo.dto.UserLoginDTO;
import com.parttime.cs353.utils.JwtUtils;
import com.parttime.cs353.utils.RequestUtils;
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
@Deprecated
//好像直接登录那边返回就行（？
@Slf4j
public class JwtFilter implements Filter {
    public JwtFilter() throws Exception {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info(filterConfig.getFilterName() + " init");
    }

    private final PublicKey publick= RsaUtils.getPublicKey("C:\\auth_key\\rsa_key.pub");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        log.info("myFilter2 begin");
        try {
            log.info("业务方法执行");
            HttpServletResponse res = (HttpServletResponse) response;
            HttpServletRequest req=(HttpServletRequest) request;

            UserLoginDTO userLoginDTO= RequestUtils.read(req,UserLoginDTO.class);
            String phone=userLoginDTO.getPhone();
            String pwd=userLoginDTO.getPassword();
//            if(pwd.equals())



        } catch (Exception e) {
            log.error("error!", e);
        }
        log.info("myFilter2 end");
    }

    @Override
    public void destroy() {
    }
}

