package com.parttime.cs353.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-31 19:03
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtherLoginDO {
    private String phone;
    private String password;
    /**
     * function description: 这里只有两种type，company和admin
     * @author: Isabella
     * @date: 2024-10-31
     * @Param:
     * @return
    **/
    private String type;
}
