package com.parttime.cs353.test;

import com.parttime.cs353.pojo.data.UserDO;
import com.parttime.cs353.pojo.dto.OtherLoginDO;
import com.parttime.cs353.pojo.dto.UserLoginDTO;
import com.parttime.cs353.service.inter.CompanyService;
import com.parttime.cs353.service.inter.UserService;
import com.parttime.cs353.utils.JwtUtils;
import com.parttime.cs353.utils.ResponseUtils;
import com.parttime.cs353.utils.RsaKeyProperties;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-31 17:35
 **/
@SpringBootTest
@Slf4j
public class LoginTest {
    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;
    @Autowired
    private RsaKeyProperties prop;
    @Test
    public void register(){
        UserLoginDTO userLoginDTO=new UserLoginDTO("123123","admin","hr");
        userService.addUser(userLoginDTO);
    }
    @Test
    public void companyRegister(){
        OtherLoginDO otherLoginDO =new OtherLoginDO("123123","company","company");
        companyService.addCompany(otherLoginDO);
    }
    @Test
    public void testing(){
        UserLoginDTO userLoginDTO=new UserLoginDTO("13199546056","reprehenderit est Excepteur mollit ad","user");
        if(userService.selectUserByPhone(userLoginDTO.getPhone())!=null){
        log.info("already register");
//            ResponseUtils.write(response, HttpServletResponse.SC_FORBIDDEN,"注册失败,账号已存在");
            return;
        }
        userService.addUser(userLoginDTO);
        UserDO userDO=userService.selectUserByPhone(userLoginDTO.getPhone());
        String token="Bearer "+ JwtUtils.generateTokenExpireInMinutes(userDO,prop.getPrivateKey(),100);
//        response.addHeader("Authorization",  token);
//        ResponseUtils.write(response,200,"成功注册");
        log.info("返回token："+token);
    }
}
