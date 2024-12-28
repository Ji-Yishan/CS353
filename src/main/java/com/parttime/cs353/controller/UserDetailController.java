package com.parttime.cs353.controller;

import com.parttime.cs353.pojo.business.*;
import com.parttime.cs353.pojo.data.EducationExperienceDO;
import com.parttime.cs353.pojo.data.ProjectExperienceDO;
import com.parttime.cs353.pojo.data.WorkExperienceDO;
import com.parttime.cs353.service.inter.InterviewService;
import com.parttime.cs353.service.inter.UserService;
import com.parttime.cs353.utils.ResponseUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-12-02 20:25
 **/
@RestController
@Slf4j
public class UserDetailController {
    @Autowired
    UserService userService;
    @Autowired
    InterviewService interviewService;


    /**
     * 编辑个人信息
     * @module 大学生
     */
    @PutMapping("/resumepage/peopleM")
    public void updateInfo(@RequestParam int uid, @RequestParam String name, @RequestParam int job_status,
                           @RequestParam String gender, @RequestParam String birthday,
                           HttpServletResponse response){
        UserDetailBO userDetailBO=new UserDetailBO(uid,name,job_status,gender,birthday);
        int i=userService.updateUserDetail(userDetailBO);
        if(i>0){
            ResponseUtils.write(response,200,"successful update");
        }else{
            ResponseUtils.write(response,400,"error occur");
        }
    }
    /**
     * 编辑个人优势
     * @module 大学生
     */
    @PutMapping("/resumepage/peopleA")
    public void updateStrength(@RequestParam String advantage,@RequestParam int uid,
                               HttpServletResponse response){
        Map<String,Object> map=new HashMap<>();
        map.put("advantage",advantage);
        map.put("uid",uid);
        int i=userService.updateUserAdvantage(map);
        if(i>0){
            ResponseUtils.write(response,200,"successful update");
        }else{
            ResponseUtils.write(response,400,"error occur");
        }
    }
    /**
     * 编辑期望工作
     * @module 大学生
     */
    @PutMapping("/resumepage/peopleD")
    public void updateExpectation(@RequestParam int uid, @RequestParam String desireIndustry, @RequestParam String desireTag,
                           @RequestParam String desiredWorktime, @RequestParam String salaryRequirement,
                           HttpServletResponse response){
        UserExpectationBO userDetailBO=new UserExpectationBO(uid,desireIndustry,desireTag,desiredWorktime,salaryRequirement);
        int i=userService.updateExpectation(userDetailBO);
        if(i>0){
            ResponseUtils.write(response,200,"successful update");
        }else{
            ResponseUtils.write(response,400,"error occur");
        }
    }
    /**
     * 编辑工作经验
     * @module 大学生
     */
    @PutMapping("/resumepage/peopleW")
    public void updateWorkExperience(@RequestBody WorkExperienceDO workExperienceDO,
                                  HttpServletResponse response){
        int i=userService.updateWorkExperience(workExperienceDO);
        if(i>0){
            ResponseUtils.write(response,200,"successful update");
        }else{
            ResponseUtils.write(response,400,"error occur");
        }
    }
    /**
     * 编辑项目经验
     * @module 大学生
     */
    @PutMapping("/resumepage/peopleE")
    public void updateProjectExperience(@RequestBody ProjectExperienceDO projectExperienceDO,
                                     HttpServletResponse response){
        int i=userService.updateProjectExperience(projectExperienceDO);
        if(i>0){
            ResponseUtils.write(response,200,"successful update");
        }else{
            ResponseUtils.write(response,400,"error occur");
        }
    }
    /**
     * 编辑教育经历
     * @module 大学生
     */
    @PutMapping("/resumepage/peopleEE")
    public void updateEducationExperience(@RequestBody EducationExperienceDO educationExperienceDO,
                                        HttpServletResponse response){
        int i=userService.updateEducationExperience(educationExperienceDO);
        if(i>0){
            ResponseUtils.write(response,200,"successful update");
        }else{
            ResponseUtils.write(response,400,"error occur");
        }
    }
    /**
     * 删除工作经验
     * @module 大学生
     */
    @DeleteMapping("/d/peopleW")
    public void deleteWorkExperience(@RequestParam int uid,@RequestParam int wid,
                                          HttpServletResponse response){
        int i=userService.deleteWorkExperience(wid);
        if(i>0){
            ResponseUtils.write(response,200,"successful delete");
        }else{
            ResponseUtils.write(response,400,"error occur");
        }
    }
    /**
     * 删除项目经验
     * @module 大学生
     */
    @DeleteMapping("/d/peopleP")
    public void deleteProjectExperience(@RequestParam int uid,@RequestParam int pid,
                                     HttpServletResponse response){
        int i=userService.deleteProjectExperience(pid);
        if(i>0){
            ResponseUtils.write(response,200,"successful delete");
        }else{
            ResponseUtils.write(response,400,"error occur");
        }
    }
    /**
     * 查看个人简历
     * @module 大学生
     */
    @GetMapping("/information/{uid}")
    public void getFullDetail(@PathVariable int uid,HttpServletResponse response){
        ResponseUtils.write(response,200,"success",userService.getFullDetail(uid));
    }
    /**
     * 添加简历
     * @module 大学生
     */
    @PutMapping("/information")
    public void updateFullDetail(@RequestBody InterviewAddBO interviewAddBO,HttpServletResponse response){
//        int i=userService.updateFullDetail(userFullDetailBO);
//        if(i>=0){
//            ResponseUtils.write(response,200,"successful update");
//        }else{
//            ResponseUtils.write(response,400,"error occur");
//        }
        int i=interviewService.insertInterview(interviewAddBO);
        if(i>0){
            ResponseUtils.write(response,200,"successful insert");
        }else{
            ResponseUtils.write(response,400,"error occur");
        }

    }
    /**
     * 用户简历状态
     * @module 大学生
     */
    @GetMapping("/information/vitae")
    public void status(@RequestParam int uid,HttpServletResponse response){
        Set<InterviewStatusBO> in=interviewService.selectInterviewStatus(uid);
        ResponseUtils.write(response,200,"success",in);
    }
    /**
     * 添加工作经验
     * @module 大学生
     */
    @PostMapping("/information/peopleDA")
    public void addWorkExperience(@RequestBody WorkExperienceBO workExperienceBO,
                                  HttpServletResponse response){
        int i=userService.insertWorkExperience(workExperienceBO);
        if(i>0){
            ResponseUtils.write(response,200,"successful insert");
        }else{
            ResponseUtils.write(response,400,"error occur");
        }
    }
    /**
     * 添加项目经验
     * @module 大学生
     */
    @PostMapping("/information/peopleEA")
    public void addProjectExperience(@RequestBody ProjectExperienceBO projectExperienceBO,
                                  HttpServletResponse response){
        int i=userService.insertProjectExperience(projectExperienceBO);
        if(i>0){
            ResponseUtils.write(response,200,"successful insert");
        }else{
            ResponseUtils.write(response,400,"error occur");
        }
    }
}
