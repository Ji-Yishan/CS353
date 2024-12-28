package com.parttime.cs353.test;

import com.parttime.cs353.dao.InterviewMapper;
import com.parttime.cs353.dao.JobMapper;
import com.parttime.cs353.pojo.business.InterviewBO;
import com.parttime.cs353.pojo.business.InterviewStatusBO;
import com.parttime.cs353.pojo.business.ProjectExperienceBO;
import com.parttime.cs353.pojo.business.UserDetailBO;
import com.parttime.cs353.pojo.data.EducationExperienceDO;
import com.parttime.cs353.pojo.data.InterviewDO;
import com.parttime.cs353.pojo.data.JobDO;
import com.parttime.cs353.pojo.data.ProjectExperienceDO;
import com.parttime.cs353.service.inter.InterviewService;
import com.parttime.cs353.service.inter.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-12-05 21:15
 **/
@SpringBootTest
public class UserDetialTest {
    @Autowired
    UserService userService;
    @Autowired
    InterviewService interviewService;
    @Autowired
    InterviewMapper interviewMapper;
    @Autowired
    JobMapper jobMapper;
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
    @Test
    public void state(){
//        System.out.println(interviewService.selectInterviewStatus(43));
        Set<InterviewStatusBO> res=new HashSet<>();
        List<InterviewDO> in=interviewMapper.selectInterviewById(43);
        for(InterviewDO i:in){
            JobDO j=jobMapper.selectJobById(i.getJid());
            InterviewStatusBO it=new InterviewStatusBO(i.getJid(),
                    j.getName(),j.getWorkingHours(),j.getSalary(),j.getTags(),i.getState());
            res.add(it);
        }
        System.out.println(res);
    }
    @Test
    public void project(){
//        ProjectExperienceBO projectExperienceBO=new ProjectExperienceBO(43,"2","2","2","2","we");
//        System.out.println(userService.insertProjectExperience(projectExperienceBO));
        EducationExperienceDO e=new EducationExperienceDO(2,44,"wer","wer","wer","wer","wer","wer");
        System.out.println(userService.updateEducationExperience(e));
    }
@Test
    public void test(){
//    List<List> job=new ArrayList<>();
//    for(int j:jid){
//        List<InterviewBO> l=interviewService.selectReceivedResume(j);
//        job.add(l);
//    }
    List<InterviewBO> l=interviewService.selectReceivedResume(10);
    System.out.println(l);
}
}
