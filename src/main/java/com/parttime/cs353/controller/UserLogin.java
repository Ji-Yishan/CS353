package com.parttime.cs353.controller;



import com.parttime.cs353.pojo.data.UserDO;
import com.parttime.cs353.service.inter.UserService;
import com.parttime.cs353.utils.JwtUtils;
import com.parttime.cs353.utils.PasswordEncoder;
import com.parttime.cs353.utils.RsaKeyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-25 22:39
 **/
@RestController
public class UserLogin {
    @Autowired
    UserService userService;
    @Autowired
    private RsaKeyProperties prop;

    /**
     * 登录
     * @module root
     */
//    todo：springsecurity，jwt，返回带有身份权限的token
    @PostMapping("/login")
    public String login(@RequestParam("phone")String phone,@RequestParam String password){
        System.out.println("aaaaaaaaaaaaaaa");
        String pwd=userService.selectUserByPhone(phone).getPwd();
        if(pwd.equals(password)){
            return JwtUtils.generateTokenExpireInMinutes(new UserDO(phone,pwd),prop.getPrivateKey(),100);
        }
        return "error";
    }
    @GetMapping("/hello")
    public String hello(){return "aga";}

}
