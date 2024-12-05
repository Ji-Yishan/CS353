package com.parttime.cs353.pojo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-12-02 21:28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceDO {
    private int uid;
    private String companyName;
    private String industryInvolved;
    private String departmentName;
    private String jobTitle;
    private String jobBeginTime;
    private String jobEndTime;
    private String jobContent;
    private int wid;
}
