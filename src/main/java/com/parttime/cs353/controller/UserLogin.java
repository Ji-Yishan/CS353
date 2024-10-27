package com.parttime.cs353.controller;



import com.parttime.cs353.config.jwt.AuthStorage;
import com.parttime.cs353.pojo.data.UserDO;
import com.parttime.cs353.service.inter.UserService;
import com.parttime.cs353.utils.JwtUtils;
import com.parttime.cs353.utils.PasswordEncoder;
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
    /**
     * 登录
     * @module root
     */
//    todo：springsecurity，jwt，返回带有身份权限的token
    @PostMapping("/login")
    public String login(@RequestParam("phone")String phone,@RequestParam String password){
        String pwd=userService.selectUserByPhone(phone).getPwd();
        if(pwd.equals(password)){
            return "yyeeea";
        }
        return "error";
    }
    private final static HashMap<String, String> USER = new HashMap<>() {
        {
            put("admin", "e10adc3949ba59abbe56e057f20f883e");
        }
    };

    @GetMapping("/logins")
    public String logins(String username, String password) {
        if (PasswordEncoder.matches(password, USER.get(username))) {
            // 模拟一个用户的数据 用户id为1  登录端为网页web  角色是admin
            UserDO userDO=new UserDO(1,"admin",password,username,0,true);
            String token= JwtUtils.generateTokenExpireInMinutes("admin",100);
            AuthStorage.setUser(token,userDO);
            return token;
        }
        return "error";
    }

    @GetMapping("/token/validate")
    public UserDO tokenValidate(String token) {
        return JwtUtils.checkToken(token);
    }

    @GetMapping("/get/Info")
    public String getInfo() {
        // 从全局环境中获取用户id
        UserDO user = AuthStorage.getUser();
        return "用户："+user.getUid() + "，请求成功";
    }


}
