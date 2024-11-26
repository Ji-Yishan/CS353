package com.parttime.cs353.dao;

import com.parttime.cs353.pojo.data.CompanyDO;
import com.parttime.cs353.pojo.dto.CompanyDTO;
import com.parttime.cs353.pojo.dto.OtherLoginDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: Isabella
 * @create: 2024-10-31 19:09
 **/
@Mapper
public interface CompanyPasswordMapper {
    CompanyDO selectCompanyByPhone(@Param("phone") String phone);
    int addCompany(OtherLoginDO otherLoginDO);
//    List<CompanyDO> selectCompanyByName(@Param("name")String name,@Param("pageSize")int pageSize,@Param("offset")int offset);
    List<CompanyDO> selectCompanyByName(@Param("name") String name);
    CompanyDO selectCompanyById(@Param("cid")int cid);
    int updateCompany(CompanyDTO companyDTO);
    int deleteCompany(int cid);
}
