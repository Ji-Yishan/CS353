package com.parttime.cs353.dao;

import com.parttime.cs353.pojo.data.EducationExperienceDO;
import com.parttime.cs353.pojo.data.ProjectExperienceDO;
import com.parttime.cs353.pojo.data.WorkExperienceDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Isabella
 * @create: 2024-12-02 21:12
 **/
@Mapper
public interface UserDetailMapper {
    int insertWorkExperience(WorkExperienceDO workExperienceDO);
    List<WorkExperienceDO> selectWorkExperience(@Param("uid")int uid);
    int deleteWorkExperience(@Param("wid") int wid);
    int updateWorkExperience(WorkExperienceDO workExperienceDO);
    int insertProjectExperience(ProjectExperienceDO projectExperienceDO);
    List<ProjectExperienceDO> selectProjectExperience(@Param("uid")int uid);
    int deleteProjectExperience(@Param("pid") int pid);
    int updateProjectExperience(ProjectExperienceDO projectExperienceDO);
    int insertEducationExperience(@Param("uid")int uid);
    List<EducationExperienceDO> selectEducationExperience(@Param("uid")int uid);
    int updateEducationExperience(EducationExperienceDO educationExperienceDO);


}
