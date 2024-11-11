package com.parttime.cs353.dao;

import com.parttime.cs353.pojo.data.CompanyDO;
import com.parttime.cs353.pojo.dto.OtherLoginDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Isabella
 * @create: 2024-10-31 19:09
 **/
@Mapper
public interface CompanyPasswordMapper {
    CompanyDO selectCompanyByPhone(@Param("phone") String phone);
    int addCompany(OtherLoginDO otherLoginDO);
    List<CompanyDO> selectCompanyByName(@Param("name")String name);
}
