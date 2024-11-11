package com.parttime.cs353.dao;

import com.parttime.cs353.pojo.data.JobDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: Isabella
 * @create: 2024-11-11 19:56
 **/
@Mapper
public interface JobMapper {
    int addJob(JobDO jobDO);
    List<JobDO> selectJobByName( @Param("name") String name);
    List<JobDO> selectJobByTag( @Param("tags") String tags);
    List<JobDO> selectJobByWorkTime( @Param("workingHours") String workingHours);
    int deleteJob(@Param("jid") int jid);
    int updateJob(JobDO jobDO);
}
