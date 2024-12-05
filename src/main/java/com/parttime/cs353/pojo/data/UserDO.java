package com.parttime.cs353.pojo.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author: Isabella
 * @create: 2024-10-25 21:12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDO  {

    private int uid;
    private String type;
    private String password;
    private String phone;
    private int status;
    private String name;
    private String gender;
    private String birthday;
    private String advantage;
    private String desireIndustry;
    private String desireTag;
    private String desiredWorktime;
    private String salaryRequirement;

    public UserDO(String phone, String pwd) {
        this.phone=phone;
        this.password=phone;
    }
}
