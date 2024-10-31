package com.parttime.cs353.controller;

import com.parttime.cs353.pojo.data.AdminDO;
import com.parttime.cs353.pojo.data.CompanyDO;
import com.parttime.cs353.pojo.dto.OtherLoginDO;
import com.parttime.cs353.service.inter.AdminService;
import com.parttime.cs353.service.inter.CompanyService;
import com.parttime.cs353.utils.JwtUtils;
import com.parttime.cs353.utils.ResponseUtils;
import com.parttime.cs353.utils.RsaKeyProperties;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-31 20:20
 **/
@Slf4j
@RestController
public class CompanyLoginController {
    @Autowired
    CompanyService companyService;
    @Autowired
    private RsaKeyProperties prop;
    @Autowired
    AdminService adminService;
    /**
     * 公司、管理员登录
     * @module root
     */
    @PostMapping("/other/login")
    public void login(@RequestBody OtherLoginDO otherLoginDO, HttpServletResponse response){
        switch (otherLoginDO.getType()){
            case "company": companyLogin(otherLoginDO,response);break;
            case "admin": adminLogin(otherLoginDO,response);break;
            default: ResponseUtils.write(response,HttpServletResponse.SC_FORBIDDEN,"登录失败,类型错误");
        }

    }
    /**
     * 注册公司账号
     * @module root
     */
    @PostMapping("/company/register")
    public void register(@RequestBody OtherLoginDO otherLoginDO, HttpServletResponse response){
        try {
            companyService.addCompany(otherLoginDO);
            CompanyDO companyDO=companyService.selectCompanyByPhone(otherLoginDO.getPhone());
            String token="Bearer "+JwtUtils.generateCompanyTokenExpireInMinutes(companyDO,prop.getPrivateKey(),100);
            response.addHeader("Authorization",  token);
            ResponseUtils.write(response,200,"成功注册");

        }catch (Exception e){
            log.info(String.valueOf(e));
            ResponseUtils.write(response,HttpServletResponse.SC_FORBIDDEN,"公司注册失败");
        }
    }

    public void companyLogin(OtherLoginDO otherLoginDO, HttpServletResponse response){
        CompanyDO companyDO=companyService.selectCompanyByPhone(otherLoginDO.getPhone());
        String pwd=companyDO.getPassword();
        if(pwd.equals(otherLoginDO.getPassword())){
            String token="Bearer "+ JwtUtils.generateCompanyTokenExpireInMinutes(companyDO,prop.getPrivateKey(),100);
            response.addHeader("Authorization",  token);
            ResponseUtils.write(response,200,"成功登录");
        }
        ResponseUtils.write(response,HttpServletResponse.SC_FORBIDDEN,"公司登录验证失败");
    }

    public void adminLogin(OtherLoginDO otherLoginDO, HttpServletResponse response){
        AdminDO adminDO=adminService.selectAdminByPhone(otherLoginDO.getPhone());
        String pwd=adminDO.getPassword();
        if(pwd.equals(otherLoginDO.getPassword())){
            String token="Bearer "+ JwtUtils.generateAdminTokenExpireInMinutes(adminDO,prop.getPrivateKey(),100);
            response.addHeader("Authorization",  token);
            ResponseUtils.write(response,200,"成功登录");
        }
        ResponseUtils.write(response,HttpServletResponse.SC_FORBIDDEN,"管理员登录验证失败");
    }
}
