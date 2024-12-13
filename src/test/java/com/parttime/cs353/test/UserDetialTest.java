package com.parttime.cs353.test;

import com.parttime.cs353.pojo.business.UserDetailBO;
import com.parttime.cs353.service.inter.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-12-05 21:15
 **/
@SpringBootTest
public class UserDetialTest {
    @Autowired
    UserService userService;
    @Test
    public void updateInfo(){
        UserDetailBO userDetailBO=new UserDetailBO(43,"sd",1,"we","wew");
        System.out.println(userService.updateUserDetail(userDetailBO));
    }
    @Test
    public void updateExpect(){
//        WorkExperienceBO workExperienceDO=new WorkExperienceBO(43,"vdf","et","erte","ert","ert","ert","ert",1);
//        System.out.println(userService.updateWorkExperience(workExperienceDO));
    }
    @Test
    public void get(){
        System.out.println(userService.getFullDetail(43));
    }

}
