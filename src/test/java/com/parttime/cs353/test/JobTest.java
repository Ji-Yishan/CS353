package com.parttime.cs353.test;

import com.parttime.cs353.dao.JobMapper;
import com.parttime.cs353.pojo.data.JobDO;
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

    @Test
    public void selectByName(){

        List<JobDO> list=jobService.selectJobByName("职位");
        for(JobDO j:list){
            System.out.println(j);
        }
    }
}
