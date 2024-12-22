package com.parttime.cs353.pojo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-11-11 19:52
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDO {
    private int jid;
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


