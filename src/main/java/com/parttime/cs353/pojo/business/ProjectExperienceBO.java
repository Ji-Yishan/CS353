package com.parttime.cs353.pojo.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-12-23 22:47
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectExperienceBO {
    private int uid;
    private String projectName;
    private String projectRole;
    private String projectTimeBegin;
    private String projectTimeEnd;
    private String projectDescription;
}
