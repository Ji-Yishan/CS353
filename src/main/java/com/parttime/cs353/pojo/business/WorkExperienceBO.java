package com.parttime.cs353.pojo.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-12-23 22:45
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceBO {
    private int uid;
    private String companyName;
    private String industryInvolved;
    private String departmentName;
    private String jobTitle;
    private String jobBeginTime;
    private String jobEndTime;
    private String jobContent;
}
