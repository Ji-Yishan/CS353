package com.parttime.cs353.pojo.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-11-21 15:30
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobBO {
    private int jid;
    private String name;

    private String tags;

    private String salary;

    private Integer recruitNum;
    private int cid;
    private String companyName;
    private int pageNum;

    public JobBO(int jid, String name, String tags, String salary, Integer recruitNum, int cid, String companyName) {
        this.jid = jid;
        this.name = name;
        this.tags = tags;
        this.salary = salary;
        this.recruitNum = recruitNum;
        this.cid = cid;
        this.companyName = companyName;
    }
}
