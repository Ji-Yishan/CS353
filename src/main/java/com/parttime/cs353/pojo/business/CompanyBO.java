package com.parttime.cs353.pojo.business;

import com.parttime.cs353.pojo.data.JobDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-11-21 15:56
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyBO {
    private int cid;
    private String industry;
    private String scale;
    private double grade;
    private String name;
    private int status;
    private List<JobDO> jobList;
    private int pageNum;


    public CompanyBO(int cid, String industry, String scale, double grade, String name, int status, List<JobDO> jobList) {
        this.cid = cid;
        this.industry = industry;
        this.scale = scale;
        this.grade = grade;
        this.name = name;
        this.status = status;
        this.jobList = jobList;
    }
}
