package com.parttime.cs353.service.inter;

import com.parttime.cs353.pojo.data.ResumeDO;

import java.util.List;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-11-13 22:48
 **/
@Deprecated
public interface ResumeService {
    int insertResume(ResumeDO resumeDO);
    int deleteResume(int uid);
    int updateResume(ResumeDO resumeDO);
    List<ResumeDO> selectResumeById(int uid);
}
