package com.parttime.cs353.service.inter;

import com.parttime.cs353.pojo.data.JobDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Isabella
 * @create: 2024-11-11 20:27
 **/
public interface JobService {
    int addJob(JobDO jobDO);
    List<JobDO> selectJobByName(String name);
    int deleteJob(int jid);
    int updateJob(JobDO jobDO);
    List<JobDO> selectJobByTag( String tags);
    List<JobDO> selectJobByWorkTime(  String workingHours);
}
