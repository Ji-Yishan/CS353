package com.parttime.cs353.pojo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-31 19:02
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDO {
    private int cid;
    private String industry;
    private String scale;
    private double grade;
    private String name;
    private String phone;
    private String password;
    private String information;
    private int status;
}
