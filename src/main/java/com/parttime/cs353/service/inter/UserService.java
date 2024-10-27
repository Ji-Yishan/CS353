package com.parttime.cs353.service.inter;


import com.parttime.cs353.pojo.data.UserDO;
import org.apache.ibatis.annotations.Param;


/**
 * @author: Isabella
 * @create: 2024-10-25 21:50
 **/
//public interface UserService extends UserDetailsService {
public interface UserService  {
    /**
     * function description:
     * @author: Isabella
     * @date: 2024-10-25
     * @Param: phone
     * @return
    **/
    UserDO selectUserByPhone(@Param("phone")String phone);
}
