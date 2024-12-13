package com.parttime.cs353.service.impl;


import com.parttime.cs353.config.jwt.SecurityUser;
import com.parttime.cs353.dao.UserDetailMapper;
import com.parttime.cs353.dao.UserPasswordMapper;
import com.parttime.cs353.pojo.bean.UserDetailBean;
import com.parttime.cs353.pojo.business.UserDetailBO;
import com.parttime.cs353.pojo.business.UserExpectationBO;
import com.parttime.cs353.pojo.business.UserFullDetailBO;
import com.parttime.cs353.pojo.data.EducationExperienceDO;
import com.parttime.cs353.pojo.data.ProjectExperienceDO;
import com.parttime.cs353.pojo.data.UserDO;
import com.parttime.cs353.pojo.data.WorkExperienceDO;
import com.parttime.cs353.pojo.dto.UserLoginDTO;
import com.parttime.cs353.service.inter.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: user service
 * @author: Isabella
 * @create: 2024-10-25 21:56
 **/
@Slf4j
@Service("userServiceImpl")
//@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserPasswordMapper userPasswordMapper;
    @Autowired
    private UserDetailMapper userDetailMapper;

    public void setUserPasswordMapper(UserPasswordMapper userPasswordMapper){
        this.userPasswordMapper=userPasswordMapper;
    }

    @Override
    public UserDO selectUserByPhone(String phone) {
        return userPasswordMapper.selectUserByPhone(phone);
    }

    @Override
    public int addUser(UserLoginDTO userLoginDTO) {
        int i=0;
        if(userPasswordMapper.selectUserByPhone(userLoginDTO.getPhone())==null){
            i+=userPasswordMapper.addUser(userLoginDTO);
            int uid=userPasswordMapper.selectUserByPhone(userLoginDTO.getPhone()).getUid();
            i+=userDetailMapper.insertWorkExperience(uid);
            i+=userDetailMapper.insertProjectExperience(uid);
            i+=userDetailMapper.insertEducationExperience(uid);
        }
        return i;
    }
    @Override
    public int updateUserDetail(UserDetailBO userDetailBO) {
        return userPasswordMapper.updateUserDetail(userDetailBO);
    }
    @Override
    public int updateUserAdvantage(Map<String, Object> map) {
        return userPasswordMapper.updateUserAdvantage(map);
    }
    @Override
    public int updateExpectation(UserExpectationBO userExpectationBO) {
        return userPasswordMapper.updateExpectation(userExpectationBO);
    }
    @Override
    public int updateWorkExperience(WorkExperienceDO workExperienceDO) {
        return userDetailMapper.updateWorkExperience(workExperienceDO);
    }

    @Override
    public int updateProjectExperience(ProjectExperienceDO projectExperienceDO) {
        return userDetailMapper.updateProjectExperience(projectExperienceDO);
    }

    @Override
    public int updateEducationExperience(EducationExperienceDO educationExperienceDO) {
        return userDetailMapper.updateEducationExperience(educationExperienceDO);
    }

    @Override
    public int deleteWorkExperience(int wid) {
        return userDetailMapper.deleteWorkExperience(wid);
    }

    @Override
    public int deleteProjectExperience(int pid) {
        return userDetailMapper.deleteProjectExperience(pid);
    }

    @Override
    public int updateFullDetail(UserFullDetailBO userFullDetailBO) {
        UserDetailBean ud=userFullDetailBO.getPersonalInfo();
        UserDetailBO udt=new UserDetailBO(ud.getUid(),ud.getName(),ud.getStatus(),ud.getGender(),ud.getBirthday());
        UserExpectationBO ue=new UserExpectationBO(ud.getUid(),ud.getDesireIndustry(),ud.getDesireTag(),ud.getDesiredWorktime(),ud.getSalaryRequirement());
        Map<String,Object> map=new HashMap<>();
        map.put("uid",ud.getUid());
        map.put("advantage",ud.getAdvantage());
        List<ProjectExperienceDO> pj=userFullDetailBO.getProjectsInfo();
        EducationExperienceDO ed=userFullDetailBO.getSchoolInfo();
        List<WorkExperienceDO> wk=userFullDetailBO.getWorkInfo();
        int i=0;
        i+=userPasswordMapper.updateUserDetail(udt);
        i+=userPasswordMapper.updateExpectation(ue);
        i+=userPasswordMapper.updateUserAdvantage(map);
        i+=userDetailMapper.updateEducationExperience(ed);
        for(ProjectExperienceDO p:pj){
            i+=userDetailMapper.updateProjectExperience(p);
        }
        for(WorkExperienceDO w:wk){
            i+=userDetailMapper.updateWorkExperience(w);
        }
        return i;
    }

    @Override
    public UserFullDetailBO getFullDetail(int uid) {
        UserDetailBean userDetailBO=userPasswordMapper.selectUserDetail(uid);
        List<ProjectExperienceDO> pro=userDetailMapper.selectProjectExperience(uid);
        EducationExperienceDO ed=userDetailMapper.selectEducationExperience(uid).get(0);
        List<WorkExperienceDO> wk=userDetailMapper.selectWorkExperience(uid);
        UserFullDetailBO userFullDetailBO=new UserFullDetailBO(userDetailBO,pro,ed,wk);
        return userFullDetailBO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUser user=new SecurityUser();
        UserDO u=userPasswordMapper.selectUserByPhone(username);
        user.setPhone(u.getPhone());
        user.setUid(u.getUid());
        user.setPassword(u.getPassword());
        user.setStatus(u.getStatus());

        return user;
    }
}
