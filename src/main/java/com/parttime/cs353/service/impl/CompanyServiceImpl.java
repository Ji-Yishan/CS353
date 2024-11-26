package com.parttime.cs353.service.impl;

import com.parttime.cs353.dao.CompanyPasswordMapper;
import com.parttime.cs353.dao.JobMapper;
import com.parttime.cs353.pojo.business.CompanyBO;
import com.parttime.cs353.pojo.data.CompanyDO;
import com.parttime.cs353.pojo.data.JobDO;
import com.parttime.cs353.pojo.dto.OtherLoginDO;
import com.parttime.cs353.service.inter.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private JobMapper jobMapper;
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
    public List<CompanyBO> selectCompanyByName(String name) {
        List<CompanyBO> result=new ArrayList<>();
//        int pageSize=5;
//        Map<String, Object> params = new HashMap<>();
//        params.put("name", name);
//        params.put("pageSize", pageSize);
//        params.put("offset", (page-1)*pageSize);
        List<CompanyDO> list=companyPasswordMapper.selectCompanyByName(name);
        int pageN;
        if(list.size()%5!=0){
            pageN=list.size()/5+1;
        }else {pageN=list.size()/5;}
        for(CompanyDO c:list){
            List<JobDO> jobDOS=jobMapper.selectJobByCid(c.getCid());
            CompanyBO companyBO=new CompanyBO(c.getCid(),c.getIndustry(),c.getScale(),c.getGrade(),
                    c.getName(),c.getStatus(),jobDOS);
            companyBO.setPageNum(pageN);
            result.add(companyBO);
        }
        return result;
    }

    @Override
    public CompanyBO selectCompanyById(int cid) {
        CompanyDO c=companyPasswordMapper.selectCompanyById(cid);
        List<JobDO> jobDOS=jobMapper.selectJobByCid(c.getCid());
        CompanyBO companyBO=new CompanyBO(c.getCid(),c.getIndustry(),c.getScale(),c.getGrade(),
                c.getName(),c.getStatus(),jobDOS);
        return companyBO;
    }
}
