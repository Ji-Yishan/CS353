package com.parttime.cs353.pojo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-12-08 13:55
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailBean {
    private int uid;
    private int status;
    private String name;
    private String gender;
    private String birthday;
    private String advantage;
    private String desireIndustry;
    private String desireTag;
    private String desiredWorktime;
    private String salaryRequirement;
}
