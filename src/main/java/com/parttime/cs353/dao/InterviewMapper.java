package com.parttime.cs353.dao;

import com.parttime.cs353.pojo.data.InterviewDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-12-13 21:23
 **/
@Mapper
public interface InterviewMapper {
    int insertInterview(InterviewDO interview);
    int updateInterview(InterviewDO interview);
    int deleteInterview(InterviewDO interview);
    List<InterviewDO> selectInterviewById(@Param("uid") int uid);
    List<InterviewDO> selectInterviewByCid(@Param("cid")int cid);
    List<InterviewDO> selectInterviewByJid(@Param("jid")int jid);

}
