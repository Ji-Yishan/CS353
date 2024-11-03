package com.parttime.cs353.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-29 22:53
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    private String phone;
    private String password;
/**
 * function description: 这里只有两种 type，一个是user另一个是hr
 * @author: Isabella
 * @date: 2024-10-31
 * @Param:
 * @return
**/
    private String type;
}
