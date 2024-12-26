package com.parttime.cs353.pojo.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-12-25 21:28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAddBO {
    private String name;

    private String tags;

    private String time;

    private String salary;

    private String salaryDetail;

    private String workingHours;

    private String requirements;
    private String description;

    private Integer recruitNum;

    private String deadline;

    private int cid;

    private String address;
}
