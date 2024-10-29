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
}
