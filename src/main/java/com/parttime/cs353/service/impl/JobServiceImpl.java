package com.parttime.cs353.service.impl;

import com.parttime.cs353.dao.CompanyPasswordMapper;
import com.parttime.cs353.dao.InterviewMapper;
import com.parttime.cs353.dao.JobMapper;
import com.parttime.cs353.pojo.business.JobAddBO;
import com.parttime.cs353.pojo.business.JobBO;
import com.parttime.cs353.pojo.data.InterviewDO;
import com.parttime.cs353.pojo.data.JobDO;
import com.parttime.cs353.service.inter.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private final int pageSize=5;

    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private CompanyPasswordMapper companyPasswordMapper;
    @Autowired
    private InterviewMapper interviewMapper;

    public void setJobMapper(JobMapper jobMapper){
        this.jobMapper=jobMapper;
    }

    @Override
    public int addJob(JobAddBO jobAddBO) {
        int i=0;
        i+=jobMapper.addJob(jobAddBO);
        return i;
    }

    @Override
    public List<JobBO> selectJobByName(String name) {
        List<JobBO> result=new ArrayList<>();
//        Map<String, Object> params = new HashMap<>();
//        params.put("name", name);
//        params.put("pageSize", pageSize);
//        params.put("offset", (page-1)*pageSize);
        List<JobDO> list=jobMapper.selectJobByName(name);
        int pageN;
        if(list.size()%5!=0){
            pageN=list.size()/5+1;
        }else {pageN=list.size()/5;}
        for(JobDO j:list){
            String companyNmae=companyPasswordMapper.selectCompanyById(j.getCid()).getName();
            JobBO jobBO=new JobBO(j.getJid(),j.getName(),j.getTags(),
                    j.getSalary(),j.getRecruitNum(),j.getCid(),companyNmae);
            jobBO.setPageNum(pageN);
            result.add(jobBO);
        }
        return result;
    }

    @Override
    public int deleteJob(int jid,int cid) {
        int c=jobMapper.selectJobById(jid).getCid();
        if(c==cid){
            return jobMapper.deleteJob(jid);
        }
        return 0;
    }

    @Override
    public int updateJob(JobDO jobDO) {
        return jobMapper.updateJob(jobDO);
    }

    @Override
    public List<JobDO> selectJobByTag(String tags) {
        return jobMapper.selectJobByTag(tags);
    }

    @Override
    public List<JobDO> selectJobByWorkTime(String workingHours) {
        return jobMapper.selectJobByWorkTime(workingHours);
    }

    @Override
    public List<JobBO> selectByCondition(Map<String, Object> map) {
//        map.put("pageSize", pageSize);
//        map.put("offset", (page-1)*pageSize);
        List<JobBO> result=new ArrayList<>();
        List<JobDO> list=jobMapper.selectByCondition(map);
        int pageN;
        if(list.size()%5!=0){
            pageN=list.size()/5+1;
        }else {pageN=list.size()/5;}
        for(JobDO j:list){
            String companyNmae=companyPasswordMapper.selectCompanyById(j.getCid()).getName();
            JobBO jobBO=new JobBO(j.getJid(),j.getName(),j.getTags(),
                    j.getSalary(),j.getRecruitNum(),j.getCid(),companyNmae);
            jobBO.setPageNum(pageN);
            result.add(jobBO);
        }
        return result;
    }

    @Override
    public JobDO selectJobById(int jid) {
        return jobMapper.selectJobById(jid);
    }

    @Override
    public List<JobDO> selectJobByCid(int cid) {
        return jobMapper.selectJobByCid(cid);
    }
}
