package com.parttime.cs353.controller;

import com.parttime.cs353.pojo.data.CompanyDO;
import com.parttime.cs353.pojo.data.JobDO;
import com.parttime.cs353.service.inter.CompanyService;
import com.parttime.cs353.service.inter.JobService;
import com.parttime.cs353.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<JobDO> getJobByName(@RequestParam String name){
        return jobService.selectJobByName(name);
    }

    /**
     * 按照公司搜索
     * @module 招聘
     */
    @GetMapping("/search/Company")
    public List<CompanyDO> getCompanyByName(@RequestParam String name){
//        todo 添加公司数据
        return companyService.selectCompanyByName(name);
    }

    /**
     * 按照标签搜索
     * @module 招聘
     */
    @GetMapping("/search/Tag")
    public List<JobDO> getJobByTag(@RequestParam String tags){
//        todo 分割tag处理搜索
        return jobService.selectJobByTag(tags);
    }

    /**
     * 按照工作时长搜索
     * @module 招聘
     */
    @GetMapping("/search/WorkingHour")
    public List<JobDO> getJobByWorkingHour(@RequestParam String workingHours){
//        todo 精确时间
        return jobService.selectJobByWorkTime(workingHours);
    }

}
