package com.parttime.cs353.test;

import com.parttime.cs353.pojo.dto.UserLoginDTO;
import com.parttime.cs353.service.inter.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-31 17:35
 **/
@SpringBootTest
public class LoginTest {
    @Autowired
    UserService userService;
    @Test
    public void register(){
        UserLoginDTO userLoginDTO=new UserLoginDTO("123123","admin","hr");
        userService.addUser(userLoginDTO);
    }
}
