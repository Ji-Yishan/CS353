//package com.parttime.cs353.config.jwt;
//
//import com.parttime.cs353.service.inter.UserService;
//import com.parttime.cs353.utils.RsaKeyProperties;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
///**
// * @Description:
// * @author: Isabella
// * @create: 2024-10-28 22:43
// **/
////@Configuration
////@EnableWebSecurity
////@EnableGlobalMethodSecurity(securedEnabled = true)
//@Slf4j
//public class WebSecurityConfig {
//    @Autowired
//    private AuthenticationConfiguration authenticationConfiguration;
//    @Autowired
//    private RsaKeyProperties prop;
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    public AuthenticationProvider daoAuthenticationProvider() {
//        AuthenticationProvider daoAuthenticationProvider = new JwtAuthenticationProvider();
////        daoAuthenticationProvider.setUserDetailsService(sysUserDetailsService);
////        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
////        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
//        AuthenticationManager pm=new ProviderManager(daoAuthenticationProvider);
//        return daoAuthenticationProvider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//
//    @Bean
//    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authenticationManager(authenticationManager(authenticationConfiguration));
//        http.authenticationProvider(new JwtAuthenticationProvider());
//
//        http.authorizeHttpRequests(authorizeHttpRequests ->
//                        authorizeHttpRequests
//                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                                .requestMatchers("/login","hello").permitAll()
//                                .anyRequest().authenticated()
//        );
//
////        log.info("setting it");
//
//        http.addFilterAt(new JwtAuthenticationFilter(authenticationManager(authenticationConfiguration),prop), UsernamePasswordAuthenticationFilter.class);
//        http.addFilterBefore(new JwtVerificationFilter( prop), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
////    @Bean
////    public WebSecurityCustomizer ignoringCustomizer() {
////        return (web) -> web.ignoring().requestMatchers("/login");
////    }
//}