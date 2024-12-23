package com.parttime.cs353.pojo.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-12-23 22:10
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewStatusBO {
    private int jid;
    private String name;
    private String workingHours;
    private String salary;
    private String tags;
    private String status;
}
