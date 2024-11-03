package com.parttime.cs353.service.inter;

import com.parttime.cs353.pojo.data.CompanyDO;
import com.parttime.cs353.pojo.dto.OtherLoginDO;

/**
 * @author: Isabella
 * @create: 2024-10-31 20:18
 **/
public interface CompanyService {
    CompanyDO selectCompanyByPhone( String phone);
    int addCompany(OtherLoginDO otherLoginDO);
}
