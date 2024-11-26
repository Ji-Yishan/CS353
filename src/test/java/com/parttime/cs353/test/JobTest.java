package com.parttime.cs353.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.parttime.cs353.dao.CompanyPasswordMapper;
import com.parttime.cs353.dao.JobMapper;
import com.parttime.cs353.pojo.business.CompanyBO;
import com.parttime.cs353.pojo.business.JobBO;
import com.parttime.cs353.pojo.data.CompanyDO;
import com.parttime.cs353.pojo.data.JobDO;
import com.parttime.cs353.pojo.dto.JobDTO;
import com.parttime.cs353.service.inter.CompanyService;
import com.parttime.cs353.service.inter.JobService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-11-11 20:59
 **/
@SpringBootTest
public class JobTest {
    @Autowired
    JobService jobService;

    @Autowired
    JobMapper jobMapper;
    @Autowired
    CompanyPasswordMapper companyPasswordMapper;
    @Autowired
    CompanyService companyService;

    @Test
    public void selectByName(){
//        Map<String, Object> params = new HashMap<>();
//        params.put("name", "职位");
//        params.put("pageSize", 5);
//        params.put("offset", 0);
        jobMapper.selectJobByCid(1);
//        List<CompanyBO> list=companyService.selectCompanyByName("公司");
//        for(CompanyBO j:list){
//            System.out.println(j);
//        }
    }

    @Test
    public void testFilter(){
//        JobDTO jobDTO=new JobDTO("5000/月");
//        Map<String, Object> map= JSONObject.parseObject(JSON.toJSONString(jobDTO),Map.class);
//        Map<String,Object> map=new HashMap<>();
////        map.put("name","职位");
//        map.put("salary","5000/月");
//        map.put("pageSize",1);
//        map.put("offset",0);
//        List<JobDO> list=jobMapper.selectByCondition(map);
//        for(JobDO j:list){
//            System.out.println(j);
//        }
//        System.out.println(jobService.selectByCondition(map,1));
//        CompanyBO companyBO=companyService.selectCompanyById(1);
        JobDO jobDO=jobMapper.selectJobById(1);
        System.out.println(jobDO);


    }
}
