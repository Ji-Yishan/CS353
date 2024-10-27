package com.parttime.cs353;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
public class Cs353Application {

    public static void main(String[] args) {
        SpringApplication.run(Cs353Application.class, args);
    }

}
