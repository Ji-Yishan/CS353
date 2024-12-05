package com.parttime.cs353.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.parttime.cs353.pojo.business.CompanyBO;
import com.parttime.cs353.pojo.business.JobBO;
import com.parttime.cs353.pojo.data.CompanyDO;
import com.parttime.cs353.pojo.data.JobDO;
import com.parttime.cs353.pojo.dto.JobDTO;
import com.parttime.cs353.service.inter.CompanyService;
import com.parttime.cs353.service.inter.JobService;
import com.parttime.cs353.utils.ResponseUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        System.out.println(jobDO);
        if(jobDO.equals(null)){
            ResponseUtils.write(response,404,"no Job found");
            return;
        }
        ResponseUtils.write(response,200,"success",jobDO);
    }


    /**
     * 按照标签搜索
     * @module 招聘
     */
    @Deprecated
    @GetMapping("/search/Tag")
    public List<JobDO> getJobByTag(@RequestParam String tags){

        return jobService.selectJobByTag(tags);
    }

    /**
     * 按照工作时长搜索
     * @module 招聘
     */
    @Deprecated
    @GetMapping("/search/WorkingHour")
    public List<JobDO> getJobByWorkingHour(@RequestParam String workingHours){
        return jobService.selectJobByWorkTime(workingHours);
    }



}
