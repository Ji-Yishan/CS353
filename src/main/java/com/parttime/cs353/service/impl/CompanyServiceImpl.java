package com.parttime.cs353.service.impl;

import com.parttime.cs353.dao.CompanyPasswordMapper;
import com.parttime.cs353.pojo.data.CompanyDO;
import com.parttime.cs353.pojo.dto.OtherLoginDO;
import com.parttime.cs353.service.inter.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-31 20:19
 **/
@Slf4j
@Service("companyServiceImpl")
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyPasswordMapper companyPasswordMapper;
    public void  setCompanyPasswordMapper(CompanyPasswordMapper companyPasswordMapper) {
         this.companyPasswordMapper=companyPasswordMapper;
    }


    @Override
    public CompanyDO selectCompanyByPhone(String phone) {
        return companyPasswordMapper.selectCompanyByPhone(phone);
    }

    @Override
    public int addCompany(OtherLoginDO otherLoginDO) {
        return companyPasswordMapper.addCompany(otherLoginDO);
    }

    @Override
    public List<CompanyDO> selectCompanyByName(String name) {
        return companyPasswordMapper.selectCompanyByName(name);
    }
}
