package com.parttime.cs353;

import com.parttime.cs353.utils.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

//@SpringBootApplication
@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
//要先用test那边生成key可能，不然会报错
@EnableConfigurationProperties(RsaKeyProperties.class)
public class Cs353Application {

    public static void main(String[] args) {
        SpringApplication.run(Cs353Application.class, args);
    }

}
