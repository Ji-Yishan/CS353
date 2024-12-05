package com.parttime.cs353.pojo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-12-02 22:04
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectExperienceDO {
    private int pid;
    private int uid;
    private String projectName;
    private String projectRole;
    private String projectTimeBegin;
    private String projectTimeEnd;
    private String projectDescription;
}
