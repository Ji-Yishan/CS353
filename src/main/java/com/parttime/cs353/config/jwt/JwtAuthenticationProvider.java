package com.parttime.cs353.config.jwt;

import com.parttime.cs353.service.inter.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-29 22:58
 **/
//@Component
//@Slf4j
//public class JwtAuthenticationProvider implements AuthenticationProvider {
//    @Autowired
//    UserService userDetailService;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        // 获取用户输入的用户名和密码
//        log.info("using jwt authentication provider");
//        String phone = authentication.getName();
//        String password = authentication.getCredentials().toString();
//        UserDetails userDetails = userDetailService.loadUserByUsername(phone);
////        todo 注册的时候还没加密
////        boolean matches = passwordEncoder.matches(password, userDetails.getPassword());
//        boolean matches=password.equals(userDetails.getPassword());
//        if(!matches){
//            throw new AuthenticationException("User password error."){};
//        }
//        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//}
