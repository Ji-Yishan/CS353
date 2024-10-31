package com.parttime.cs353.dao;


import com.parttime.cs353.pojo.data.UserDO;
import com.parttime.cs353.pojo.dto.UserLoginDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: Isabella
 * @create: 2024-10-25 21:21
 **/
@Mapper
public interface UserPasswordMapper{
    /**
     * function description:
     * @author: Isabella
     * @date: 2024-10-25

     * @param phone
     * @return
     **/
    UserDO selectUserByPhone(@Param("phone")String phone);
    int addUser(UserLoginDTO userLoginDTO);

}
