package com.parttime.cs353.pojo.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-12-02 21:04
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExpectationBO {
    private int uid;
    private String desireIndustry;
    private String desireTag;
    private String desiredWorktime;
    private String salaryRequirement;
}
