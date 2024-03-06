package com.broad.emc.module.service.impl.common;


import com.broad.emc.module.entity.Admin;
import com.broad.emc.module.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * shiro框架 自定义Realm 登录授权验证
 * @tkh
 * 2024-03-04
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    AdminService adminService;
    
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        
        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        // 通过用户名 获取数据库中真实用户
        Admin admin = adminService.findAdminByAccount(userToken.getUsername());
        if(admin == null){
            return null;
        }
        //把用户存入session
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("user",admin);
        //密码认证，shiro自动完成
        return new SimpleAuthenticationInfo(admin,admin.getPassword(),"");
    }


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //通过角色授权 admin/user  
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Admin admin= (Admin) principals.getPrimaryPrincipal();
        
        //根据用户名查询数据库中用户权限
        admin= adminService.findAdminByAccount(admin.getAccount());
        System.out.println("权限=="+admin.getType());
        authorizationInfo.addRole(admin.getType());
        return authorizationInfo;

    }
    
}
