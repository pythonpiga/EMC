package com.broad.emc.module.controller;


import com.broad.emc.common.util.DateUtil;
import com.broad.emc.common.util.MD5Util;
import com.broad.emc.common.util.StringUtil;
import com.broad.emc.module.entity.Admin;
import com.broad.emc.module.service.AdminService;

import com.broad.emc.module.vo.AdminVo;
import com.broad.emc.module.vo.ReturnData;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/emc/admin")
public class AdminController {

    @Value("${system.encode.salt}")
    private String encodeSalt;

    @Resource
    private AdminService adminService;

//    @RequestMapping("/createAdmin")
//    public ReturnData createAdmin(@RequestBody AdminVo adminVo) {
//        ReturnData ret = ReturnData.getSuccessData();
//        try {
//            if (StringUtil.isEmpty(adminVo.getAccount()) || StringUtil.isEmpty(adminVo.getPassword()) || StringUtil.isEmpty(adminVo.getType())) {
//                return ReturnData.getFailData("缺少参数");
//            }
//            if (adminService.findAdminByAccount(adminVo.getAccount()) == null) {
//                Admin admin = new Admin();
//                admin.setAccount(adminVo.getAccount());
//                admin.setPassword(MD5Util.encode(adminVo.getPassword(), encodeSalt));
//                admin.setCreateTime(DateUtil.getTime2());
//                admin.setUpdateTime(DateUtil.getTime2());
//                admin.setType(adminVo.getType());
//                adminService.createAdmin(admin);
//            } else {
//                return ReturnData.getFailData("账号已存在");
//            }
//        } catch (Exception e) {
//            log.error("创建管理员,异常：{}", e);
//            return ReturnData.getExceptionData("创建管理员,异常");
//        }
//        return ret;
//
//    }

    //@MyArchivesLog(operteContent = "登录")
//    @RequestMapping("/login")
//    public ReturnData adminLogin(@RequestBody AdminVo adminVo) {
//        ReturnData ret = ReturnData.getSuccessData();
//        try {
//            if (StringUtil.isEmpty(adminVo.getAccount()) || StringUtil.isEmpty(adminVo.getPassword())) {
//                return ReturnData.getFailData("缺少参数");
//            }
//            Admin admin = adminService.findAdminByAccount(adminVo.getAccount());
//            if (admin != null) {
//                String encodePassword = MD5Util.encode(adminVo.getPassword(), encodeSalt);
//                if (encodePassword.equals(admin.getPassword())) {
//                    String token = TokenUtil.sign(adminVo.getAccount());
//                    ret.setData(token);
//                    //new LogAspect().setUSerMsg(adminVo.getAccount());
//                } else {
//                    return ReturnData.getFailData("账号或密码错误");
//                }
//            } else {
//                return ReturnData.getFailData("账号不存在");
//            }
//        } catch (Exception e) {
//            log.error("管理员登录,异常：{}", e);
//            return ReturnData.getExceptionData("管理员登录,异常");
//        }
//        return ret;
//    }
//

    @RequestMapping("/login")
    public ReturnData adminLogin(@RequestBody AdminVo adminVo) {
        ReturnData ret = ReturnData.getSuccessData();
       
        if (StringUtil.isEmpty(adminVo.getAccount()) || StringUtil.isEmpty(adminVo.getPassword())) {
            return ReturnData.getFailData("缺少参数");
        }

        //Subject: 用户
        Subject subject= SecurityUtils.getSubject();
        //封装用户的登录数据
        String encodePassword = MD5Util.encode(adminVo.getPassword(), encodeSalt);  //密码MD5加密
        UsernamePasswordToken token= new UsernamePasswordToken(adminVo.getAccount(),encodePassword);

        try {
            subject.login(token);       //shiro调用login时会调用自定义的realm的认证方法
            subject.getSession().setTimeout(5*60*60*1000);  //5小时有效期
            ret.setData(token);
        }catch (UnknownAccountException e) {
            return ReturnData.getFailData("账号错误或账号不存在");
        } catch (IncorrectCredentialsException e){
            return ReturnData.getFailData("密码错误");

        }
        
        return ret;
    }

    @RequestMapping("/changePassword")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData changePassword(@RequestBody AdminVo adminVo) {
        ReturnData ret = ReturnData.getSuccessData();
        try {
            Admin admin = adminService.findAdminByAccount(adminVo.getAccount());
            String encodePassword = MD5Util.encode(adminVo.getPassword(), encodeSalt);
            if (encodePassword.equals(admin.getPassword())) {
                String encodeNewPassword = MD5Util.encode(adminVo.getNewPassword(), encodeSalt);
                if (encodePassword.equals(encodeNewPassword)) {
                    return ReturnData.getFailData("新密码与原始密码不能相同！");
                } else {
                    adminService.updatePassword(encodeNewPassword, DateUtil.getTime2(), adminVo.getAccount());    
                }
            } else {
                return ReturnData.getFailData("原始密码错误！");
            }
        } catch (Exception e) {
            log.error("修改密码,异常：{}", e);
            return ReturnData.getExceptionData("修改密码,异常");
        }
        return ret;
    }
//
//    @RequestMapping("/checkAuth")
//    public ReturnData checkAuth(@RequestParam String account) {
//        ReturnData ret = ReturnData.getSuccessData();
//        try {
//            Admin admin = adminService.findAdminByAccount(account);
//            
//            //跳过登录验证时设置 @tkh
//            if(admin==null){
//                ret.setData(false);
//            }else{
//                String type = admin.getType();
//                if (type.equals(CommonConstant.SUPER_ADMIN)) {
//                    ret.setData(true);
//                } else {
//                    ret.setData(false);
//                }
//            }
//            
//            
//        } catch (Exception e) {
//            log.error("权限校验,异常：{}", e);
//            return ReturnData.getExceptionData("权限校验,异常");
//        }
//        
//        return ret;
//    }


    //无认证 跳转登录
    @RequestMapping("/toLogin")
    public ReturnData Unauthorized(){
        ReturnData ret=ReturnData.getSuccessData();
        ret.setCode("401");
        ret.setMsg("身份认证失败，请先登录");
        return ret;
    }

   
}
