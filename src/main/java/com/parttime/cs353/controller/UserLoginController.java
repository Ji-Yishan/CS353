package com.parttime.cs353.controller;



import com.parttime.cs353.pojo.data.UserDO;
import com.parttime.cs353.pojo.dto.UserLoginDTO;
import com.parttime.cs353.service.inter.UserService;
import com.parttime.cs353.utils.JwtUtils;
import com.parttime.cs353.utils.ResponseUtils;
import com.parttime.cs353.utils.RsaKeyProperties;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-25 22:39
 **/
@RestController
@Slf4j
public class UserLoginController {
    @Autowired
    UserService userService;
    @Autowired
    private RsaKeyProperties prop;

    /**
     * 登录
     * @module root
     */

    @PostMapping("/login")
    public void login(@RequestBody UserLoginDTO userLoginDTO, HttpServletResponse response){
        UserDO userDO=userService.selectUserByPhone(userLoginDTO.getPhone());
        String pwd=userDO.getPassword();
        if(pwd.equals(userLoginDTO.getPassword())){
            String token="Bearer "+JwtUtils.generateTokenExpireInMinutes(userDO,prop.getPrivateKey(),100);
            response.addHeader("Authorization",  token);
            log.info("返回token："+token);
            ResponseUtils.write(response,200,"成功登录");
        }
        ResponseUtils.write(response,HttpServletResponse.SC_FORBIDDEN,"用户登录验证失败");
    }
    /**
     * 注册
     * @module root
     */
    @PostMapping("/register")
    public void register(@RequestBody UserLoginDTO userLoginDTO,HttpServletResponse response){
        try {
//            todo 防止同一个phone生成多个账号。。
            userService.addUser(userLoginDTO);
            UserDO userDO=userService.selectUserByPhone(userLoginDTO.getPhone());
            String token="Bearer "+JwtUtils.generateTokenExpireInMinutes(userDO,prop.getPrivateKey(),100);
            response.addHeader("Authorization",  token);
            ResponseUtils.write(response,200,"成功注册");
            log.info("返回token："+token);
        }catch (Exception e){
            log.info(String.valueOf(e));
            ResponseUtils.write(response,HttpServletResponse.SC_FORBIDDEN,"注册失败");
        }
    }
    @GetMapping("/hello")
    public String test(HttpServletResponse response){
//        ResponseUtils.write(response,200,"成功跨域");
        return "yeahhhhhhh";
    }


}
