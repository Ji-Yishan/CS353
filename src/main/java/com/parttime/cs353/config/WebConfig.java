package com.parttime.cs353.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-30 00:02
 **/
@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean testFilterRegistration() throws Exception {
        FilterRegistrationBean registration = new FilterRegistrationBean(new TokenFilter());
        registration.addUrlPatterns("/*"); //
        registration.setName("tokeFilter");
        return registration;
    }
}
