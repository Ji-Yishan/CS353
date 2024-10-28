package com.parttime.cs353.service.impl;


import com.parttime.cs353.config.jwt.SecurityUser;
import com.parttime.cs353.dao.UserPasswordMapper;
import com.parttime.cs353.pojo.data.UserDO;
import com.parttime.cs353.service.inter.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: user service
 * @author: Isabella
 * @create: 2024-10-25 21:56
 **/
@Slf4j
@Service("userServiceImpl")
//@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserPasswordMapper userPasswordMapper;

    public void setUserPasswordMapper(UserPasswordMapper userPasswordMapper){
        this.userPasswordMapper=userPasswordMapper;
    }

    @Override
    public UserDO selectUserByPhone(String phone) {
        return userPasswordMapper.selectUserByPhone(phone);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUser user=new SecurityUser();
        UserDO u=userPasswordMapper.selectUserByPhone(username);
        user.setUsername(u.getPhone());
        user.setId(u.getUid());
        user.setPassword(u.getPwd());
        user.setStatus(u.getStatus());
        List<UserDO> list=new ArrayList<>();
        list.add(u);
        user.setSysRoles(list);
        return user;
    }
}
