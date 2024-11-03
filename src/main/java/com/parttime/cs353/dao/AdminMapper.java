package com.parttime.cs353.dao;

import com.parttime.cs353.pojo.data.AdminDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: Isabella
 * @create: 2024-10-31 19:09
 **/
@Mapper
public interface AdminMapper {
    AdminDO selectAdminByPhone(@Param("phone") String phone);
}
