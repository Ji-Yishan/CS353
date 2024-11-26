package com.parttime.cs353.pojo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-11-13 21:36
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDO {
    private int uid;
    private String name;
    private String gender;
    private int age;
    private String skills;
    private String workExperience;
    private String description;
    private String jobPreference;
}
