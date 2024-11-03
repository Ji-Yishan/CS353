package com.parttime.cs353.service.inter;

import com.parttime.cs353.pojo.data.AdminDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author: Isabella
 * @create: 2024-10-31 20:40
 **/
public interface AdminService {
    AdminDO selectAdminByPhone(String phone);
}
