package com.parttime.cs353.service.impl;

import com.parttime.cs353.dao.AdminMapper;
import com.parttime.cs353.pojo.data.AdminDO;
import com.parttime.cs353.service.inter.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-31 20:41
 **/
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public AdminDO selectAdminByPhone(String phone) {
        return adminMapper.selectAdminByPhone(phone);
    }
}
