package com.parttime.cs353.service.inter;


import com.parttime.cs353.pojo.business.UserDetailBO;
import com.parttime.cs353.pojo.business.UserExpectationBO;
import com.parttime.cs353.pojo.data.EducationExperienceDO;
import com.parttime.cs353.pojo.data.ProjectExperienceDO;
import com.parttime.cs353.pojo.data.UserDO;
import com.parttime.cs353.pojo.data.WorkExperienceDO;
import com.parttime.cs353.pojo.dto.UserLoginDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;


/**
 * @author: Isabella
 * @create: 2024-10-25 21:50
 **/
public interface UserService extends UserDetailsService {

    UserDO selectUserByPhone(@Param("phone")String phone);
    int addUser(UserLoginDTO userLoginDTO);
    int updateUserDetail(UserDetailBO userDetailBO);
    int updateUserAdvantage(Map<String,Object> map);
    int updateExpectation(UserExpectationBO userExpectationBO);
    int updateWorkExperience(WorkExperienceDO workExperienceDO);
    int updateProjectExperience(ProjectExperienceDO projectExperienceDO);
    int updateEducationExperience(EducationExperienceDO educationExperienceDO);
    int deleteWorkExperience(int wid);
    int deleteProjectExperience( int pid);

}
