package com.parttime.cs353.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-11-19 17:03
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
    private String tags;

    private String time;

    private String salary;

    private String salaryDetail;

    private String workingHours;

    private String requirements;
    private String description;

    private Integer recruitNum;

    private String deadline;
    private String address;
//    public JobDTO(String salary){
//        this.salary=salary;
//    }
}
