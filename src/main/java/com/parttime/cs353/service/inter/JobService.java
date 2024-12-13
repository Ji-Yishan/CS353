package com.parttime.cs353.service.inter;

import com.parttime.cs353.pojo.business.JobBO;
import com.parttime.cs353.pojo.data.JobDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: Isabella
 * @create: 2024-11-11 20:27
 **/
public interface JobService {
    int addJob(JobDO jobDO);
    List<JobBO> selectJobByName(String name);
    int deleteJob(int jid);
    int updateJob(JobDO jobDO);
    List<JobDO> selectJobByTag( String tags);
    List<JobDO> selectJobByWorkTime(  String workingHours);
    List<JobBO> selectByCondition(Map<String, Object> map);
    JobDO selectJobById(int jid);
    List<JobDO> selectJobByCid(int cid);
}
