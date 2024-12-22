package com.parttime.cs353.pojo.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-12-22 19:51
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewBO {
    private String name;
    private int uid;
    private String state;
}
