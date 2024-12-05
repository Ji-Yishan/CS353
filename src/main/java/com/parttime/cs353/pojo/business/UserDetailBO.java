package com.parttime.cs353.pojo.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-12-02 20:28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailBO {
    private int uid;
    private String name;
    private int status;
    private String gender;
    private String birthday;
}
