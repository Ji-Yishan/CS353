package com.parttime.cs353.pojo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-11-13 22:07
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeFileDO {
    private int rid;
    private int uid;
    private String file;
    private String name;
}
