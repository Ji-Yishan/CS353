package com.parttime.cs353.pojo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Isabella
 * @create: 2024-10-25 21:12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
//public class UserDO implements GrantedAuthority {
public class UserDO {
    private int uid;
    private String type;
    private String pwd;
    private String phone;
    private int status;
//    todo 加一个不影响数据库
    private boolean valid;

//    @Override
//    public String getAuthority() {
//        return type;
//    }
}
