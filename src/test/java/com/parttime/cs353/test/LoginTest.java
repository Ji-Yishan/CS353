package com.parttime.cs353.test;

import com.parttime.cs353.pojo.dto.OtherLoginDO;
import com.parttime.cs353.pojo.dto.UserLoginDTO;
import com.parttime.cs353.service.inter.CompanyService;
import com.parttime.cs353.service.inter.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-31 17:35
 **/
@SpringBootTest
public class LoginTest {
    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;
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
}
