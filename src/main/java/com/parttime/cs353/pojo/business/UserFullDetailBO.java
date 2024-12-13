package com.parttime.cs353.pojo.business;

import com.parttime.cs353.pojo.bean.UserDetailBean;
import com.parttime.cs353.pojo.data.EducationExperienceDO;
import com.parttime.cs353.pojo.data.ProjectExperienceDO;
import com.parttime.cs353.pojo.data.UserDO;
import com.parttime.cs353.pojo.data.WorkExperienceDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-12-05 22:57
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFullDetailBO {
    private UserDetailBean personalInfo;
    private List<ProjectExperienceDO> projectsInfo;
    private EducationExperienceDO schoolInfo;
    private List<WorkExperienceDO> workInfo;

}
