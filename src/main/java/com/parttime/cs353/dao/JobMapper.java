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
//    List<JobDO> selectJobByName( @Param("name") String name,@Param("pageSize")int pageSize,@Param("offset")int offset);
    List<JobDO> selectJobByName(@Param("name") String name);
    List<JobDO> selectJobByTag( @Param("tags") String tags);
    List<JobDO> selectJobByWorkTime( @Param("workingHours") String workingHours);
    int deleteJob(@Param("jid") int jid);
    int updateJob(JobDO jobDO);
    List<JobDO> selectByCondition(Map<String, Object> map);
    JobDO selectJobById(@Param("jid")int jid);
    List<JobDO> selectJobByCid(@Param("cid")int cid);
}
