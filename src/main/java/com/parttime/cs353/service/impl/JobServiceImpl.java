package com.parttime.cs353.service.impl;

import com.parttime.cs353.dao.JobMapper;
import com.parttime.cs353.pojo.data.JobDO;
import com.parttime.cs353.service.inter.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-11-11 20:28
 **/
@Slf4j
@Service("jobServiceImpl")
public class JobServiceImpl implements JobService {

    @Autowired
    private JobMapper jobMapper;

    public void setJobMapper(JobMapper jobMapper){
        this.jobMapper=jobMapper;
    }

    @Override
    public int addJob(JobDO jobDO) {
        return jobMapper.addJob(jobDO);
    }

    @Override
    public List<JobDO> selectJobByName(String name) {
        return jobMapper.selectJobByName(name);
    }

    @Override
    public int deleteJob(int jid) {
        return 0;
    }

    @Override
    public int updateJob(JobDO jobDO) {
        return 0;
    }

    @Override
    public List<JobDO> selectJobByTag(String tags) {
        return jobMapper.selectJobByTag(tags);
    }

    @Override
    public List<JobDO> selectJobByWorkTime(String workingHours) {
        return jobMapper.selectJobByWorkTime(workingHours);
    }
}
