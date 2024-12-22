package com.parttime.cs353.dao;

import com.parttime.cs353.pojo.data.ResumeDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Isabella
 * @create: 2024-11-13 22:24
 **/
@Deprecated
@Mapper
public interface ResumeMapper {
    int insertResume(ResumeDO resumeDO);
    int deleteResume(int uid);
    int updateResume(ResumeDO resumeDO);
    List<ResumeDO> selectResumeById(int uid);
}
