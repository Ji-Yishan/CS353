package com.parttime.cs353.controller;



import com.parttime.cs353.pojo.data.UserDO;
import com.parttime.cs353.service.inter.UserService;
import com.parttime.cs353.utils.JwtUtils;
import com.parttime.cs353.utils.ResponseUtils;
import com.parttime.cs353.utils.RsaKeyProperties;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public void login(@RequestParam("phone")String phone,@RequestParam String password,HttpServletResponse response){
        UserDO userDO=userService.selectUserByPhone(phone);
        String pwd=userDO.getPassword();
        if(pwd.equals(password)){
            String token="Bearer "+JwtUtils.generateTokenExpireInMinutes(userDO,prop.getPrivateKey(),100);
            response.addHeader("Authorization",  token);
            ResponseUtils.write(response,200,"成功登录");
        }
        ResponseUtils.write(response,HttpServletResponse.SC_FORBIDDEN,"用户登录验证失败");
    }
    @GetMapping("/hello/oi")
    public String hello(){return "aga";}

}
