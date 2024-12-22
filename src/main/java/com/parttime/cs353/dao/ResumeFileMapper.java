package com.parttime.cs353.dao;

import com.parttime.cs353.pojo.data.ResumeDO;
import com.parttime.cs353.pojo.data.ResumeFileDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Isabella
 * @create: 2024-11-13 21:57
 **/
@Deprecated
@Mapper
public interface ResumeFileMapper {
    int insertResume(ResumeFileDO resumeFileDO);
    int deleteResume(int uid);
    int updateResume(ResumeFileDO resumeFileDO);
    List<ResumeFileDO> selectResume(int uid);
}
