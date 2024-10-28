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
public class UserDO implements GrantedAuthority {

    private int uid;
    private String type;
    private String pwd;
    private String phone;
    private int status;
    private String name;
    @JsonIgnore
    private boolean valid;

    @Override
    public String getAuthority() {
        return type;
    }
    public UserDO(String phone,String pwd){
        this.phone=phone;
        this.pwd=pwd;
    }
}
