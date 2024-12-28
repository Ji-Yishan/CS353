package com.parttime.cs353.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.parttime.cs353.pojo.business.CompanyBO;
import com.parttime.cs353.pojo.business.InterviewBO;
import com.parttime.cs353.pojo.business.JobAddBO;
import com.parttime.cs353.pojo.business.JobBO;
import com.parttime.cs353.pojo.data.CompanyDO;
import com.parttime.cs353.pojo.data.InterviewDO;
import com.parttime.cs353.pojo.data.JobDO;
import com.parttime.cs353.pojo.dto.JobDTO;
import com.parttime.cs353.service.inter.CompanyService;
import com.parttime.cs353.service.inter.InterviewService;
import com.parttime.cs353.service.inter.JobService;
import com.parttime.cs353.utils.ResponseUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-11-11 20:37
 **/
@RestController
@Slf4j
public class JobController {
    @Autowired
    JobService jobService;
    @Autowired
    CompanyService companyService;
    @Autowired
    InterviewService interviewService;
    /**
     * 搜索
     * @module 招聘
     */
    @GetMapping("/search/Job")
    public void getJobByName(@RequestParam String name, HttpServletResponse response){
        try {
            List<JobBO> list=jobService.selectJobByName(name);
            if(list.isEmpty()){
                ResponseUtils.write(response,404,"no resource");
                return;
            }
            ResponseUtils.write(response,200,"success",list);
        }catch (Exception e){
            log.info(e.toString());
            ResponseUtils.write(response,404,"no resource");
        }



    }

    /**
     * 按照公司搜索
     * @module 招聘
     */
    @GetMapping("/search/Company")
    public void getCompanyByName(@RequestParam String name,HttpServletResponse response){
        try {
            List<CompanyBO> list= companyService.selectCompanyByName(name);
            if(list.isEmpty()){
                ResponseUtils.write(response,404,"no current resource");
                return;
            }
            ResponseUtils.write(response,200,"success",list);
        }catch (Exception e){
            log.debug(e.toString());
            ResponseUtils.write(response,404,"no resource");
        }
    }
    /**
     * 过滤搜索
     * @module 招聘
     */
    @PostMapping("/search/filter")
    public void searchJobWithFilter(@RequestBody JobDTO jobDO,
                                    HttpServletResponse response){
        try {
            Map<String, Object> conditions= JSONObject.parseObject(JSON.toJSONString(jobDO),Map.class);
            System.out.println(conditions.toString());
            List<JobBO> list=jobService.selectByCondition(conditions);
            if(list.isEmpty()){
                ResponseUtils.write(response,404,"no resource found");
                return;
            }
            ResponseUtils.write(response,200,"success",list);
        }catch (Exception e){
            log.info(e.toString());
            ResponseUtils.write(response,404,"no resource");
        }

    }

    /**
     * 工作详情
     * @module 公司
     */
    @GetMapping("/details/job/{jid}")
    public void homepage(@PathVariable int jid,HttpServletResponse response){
        JobDO jobDO=jobService.selectJobById(jid);
//        System.out.println(jobDO);
        if(jobDO.equals(null)){
            ResponseUtils.write(response,404,"no Job found");
            return;
        }
        ResponseUtils.write(response,200,"success",jobDO);
    }
    /**
     * 查看发布岗位
     * @module 公司
     */
    @GetMapping("/company/job/{cid}")
    public void getCompanyJob(@PathVariable int cid,HttpServletResponse response){
        ResponseUtils.write(response,200,"success",jobService.selectJobByCid(cid));
    }
    /**
     * 判断工作简历
     * @module 公司
     */
    @PutMapping("/jobC")
    public void changeState(@RequestBody InterviewDO interviewDO,HttpServletResponse response){
        int i=interviewService.updateInterview(interviewDO);
        if(i>0){
            ResponseUtils.write(response,200,"success");
        }else{
            ResponseUtils.write(response,400,"fail to update");
        }

    }
    /**
     * 修改岗位需求
     * @module 公司
     */
    @PutMapping("/resumepage/company/job")
    public void updateJob(@RequestBody JobDO jobDO,HttpServletResponse response){
        int i= jobService.updateJob(jobDO);
        if(i>0){
            ResponseUtils.write(response,200,"success");
        }else{
            ResponseUtils.write(response,400,"fail to update");
        }
    }
    /**
     * 查看岗位收到简历
     * @module 公司
     */
    @GetMapping("/jobG")
    public void getResume(@RequestParam int jid,HttpServletResponse response){
//        System.out.println(jid);
        List<InterviewBO> l=interviewService.selectReceivedResume(jid);
        if(l.size()>0){
            ResponseUtils.write(response,200,"success",l);
        }
        ResponseUtils.write(response,404,"not found");
    }
    /**
     * 删除岗位需求
     * @module 公司
     */
    @DeleteMapping("/resumepage/company/jobD")
    public void deleteJob(@RequestParam int jid,@RequestParam int cid,HttpServletResponse response){
        int i= jobService.deleteJob(jid,cid);
        if(i>0){
            ResponseUtils.write(response,200,"success");
        }else{
            ResponseUtils.write(response,400,"fail to delete");
        }
    }
    /**
     * 新增岗位
     * @module 公司
     */
    @PostMapping("/new/company/job")
    public void addJob(@RequestBody JobAddBO jobAddBO,HttpServletResponse response){
        int i= jobService.addJob(jobAddBO);
        if(i>0){
            ResponseUtils.write(response,200,"success");
        }else{
            ResponseUtils.write(response,400,"fail to insert");
        }
    }

}

