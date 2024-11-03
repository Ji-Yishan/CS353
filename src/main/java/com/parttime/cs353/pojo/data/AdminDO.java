package com.parttime.cs353.pojo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-31 20:38
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDO {
    private int adminId;
    private String name;
    private String password;
    private String phone;
}
