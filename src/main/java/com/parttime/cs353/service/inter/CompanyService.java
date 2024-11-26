package com.parttime.cs353.service.inter;

import com.parttime.cs353.pojo.business.CompanyBO;
import com.parttime.cs353.pojo.data.CompanyDO;
import com.parttime.cs353.pojo.dto.OtherLoginDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Isabella
 * @create: 2024-10-31 20:18
 **/
public interface CompanyService {
    CompanyDO selectCompanyByPhone( String phone);
    int addCompany(OtherLoginDO otherLoginDO);
    List<CompanyBO> selectCompanyByName(String name);
    CompanyBO selectCompanyById(int cid);
}
