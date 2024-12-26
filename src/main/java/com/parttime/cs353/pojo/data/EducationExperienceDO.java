package com.parttime.cs353.pojo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-12-02 22:10
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationExperienceDO {
    private int eid;
    private int uid;
    private String schoolName;
    private String majorName;
    private String timeBegin;
    private String timeEnd;
    private String experienceInSchool;
    private String educationBackground;
}
