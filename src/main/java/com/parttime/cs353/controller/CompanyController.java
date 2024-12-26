package com.parttime.cs353.controller;

import com.parttime.cs353.pojo.business.CompanyBO;
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
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-31 20:20
 **/
@Slf4j
@RestController
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @Autowired
    private RsaKeyProperties prop;
    @Autowired
    AdminService adminService;
    /**
     * 公司、管理员登录
     * @module 公司
     */
    @PostMapping("/other/login")
    public void login(@RequestBody OtherLoginDO otherLoginDO, HttpServletResponse response){
        switch (otherLoginDO.getType()){
            case "company": companyLogin(otherLoginDO,response);break;
            case "admin": adminLogin(otherLoginDO,response);break;
            default: ResponseUtils.write(response,HttpServletResponse.SC_FORBIDDEN,"fail to login");
        }

    }
    /**
     * 注册公司账号
     * @module 公司
     */
    @PostMapping("/company/register")
    public void register(@RequestBody OtherLoginDO otherLoginDO, HttpServletResponse response){
        try {
            if(companyService.selectCompanyByPhone(otherLoginDO.getPhone())!=null){
                ResponseUtils.write(response,HttpServletResponse.SC_FORBIDDEN,"fail to register, account exist");
                return;
            }
            companyService.addCompany(otherLoginDO);
            CompanyDO companyDO=companyService.selectCompanyByPhone(otherLoginDO.getPhone());
            String token="Bearer "+JwtUtils.generateCompanyTokenExpireInMinutes(companyDO,prop.getPrivateKey(),100);
            response.addHeader("Authorization",  token);
            ResponseUtils.write(response,200,"successfully register");

        }catch (Exception e){
            log.info(String.valueOf(e));
            ResponseUtils.write(response,HttpServletResponse.SC_FORBIDDEN,"fail register");
        }
    }
    /**
     * 公司主页
     * @module 公司
     */
    @GetMapping("/details/company/{cid}")
    public void homepage(@PathVariable int cid, HttpServletResponse response){
        CompanyBO companyBO=companyService.selectCompanyById(cid);
        if(companyBO.equals(null)){
            ResponseUtils.write(response,404,"no company found");
            return;
        }
        ResponseUtils.write(response,200,"success",companyBO);


    }


    public void companyLogin(OtherLoginDO otherLoginDO, HttpServletResponse response){
        CompanyDO companyDO=companyService.selectCompanyByPhone(otherLoginDO.getPhone());
        String pwd=companyDO.getPassword();
        System.out.println(pwd+" "+otherLoginDO.getPassword());
        System.out.println(pwd.equals(otherLoginDO.getPassword()));
        if(pwd.equals(otherLoginDO.getPassword())){
            String token="Bearer "+ JwtUtils.generateCompanyTokenExpireInMinutes(companyDO,prop.getPrivateKey(),100);
            response.addHeader("Authorization",  token);
            ResponseUtils.write(response,200,"successful login");
        }
        ResponseUtils.write(response,HttpServletResponse.SC_FORBIDDEN,"login verification fail");
    }

    public void adminLogin(OtherLoginDO otherLoginDO, HttpServletResponse response){
        AdminDO adminDO=adminService.selectAdminByPhone(otherLoginDO.getPhone());
        String pwd=adminDO.getPassword();
        if(pwd.equals(otherLoginDO.getPassword())){
            String token="Bearer "+ JwtUtils.generateAdminTokenExpireInMinutes(adminDO,prop.getPrivateKey(),100);
            response.addHeader("Authorization",  token);
            ResponseUtils.write(response,200,"successful login");
        }
        ResponseUtils.write(response,HttpServletResponse.SC_FORBIDDEN,"administration login fail");
    }
}
