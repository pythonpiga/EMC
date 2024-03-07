package com.broad.emc.common.config;

import com.broad.emc.module.service.impl.common.UserRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 */
@Configuration
public class ShiroConfig {

    //创建realm对象，自定义认证授权类 ：第一步
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //配置安全管理器DefaultWebSecurityManager ：第二步
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    //ShiroFilterFactoryBean ：第三步
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置 SecurityManager 
        bean.setSecurityManager(securityManager);
        
        
        /*
         * 添加Shiro的内置过滤器
         * 前后端分离无需设置url
         * anno:无需认证就可以访问
         * authc:必须认证才能访问
         * user:必须拥有 记住我 功能才能用
         * perms:拥有对某个资源的权限才能访问
         * role:拥有某个角色权限 才能访问
         * 
         * */
        Map<String,String> filterMap = new LinkedHashMap<>();
        filterMap.put("/logout", "logout");         // logout会清除session 退出登录
        filterMap.put("/emc/admin/login", "anon");  //登录页面 放行
        filterMap.put("/emc/**", "authc");          // **代表所有路径 除以上路径外的管理员页面都拦截
        
        //设置登录页 默认login.jsp 前后端分离返回json等数据
        bean.setLoginUrl("/emc/admin/toLogin");
        bean.setFilterChainDefinitionMap(filterMap);
        // 未授权页面
        //bean.setUnauthorizedUrl("/noauth");

        return bean;
    }


    /**
     * 以下两个方法解决 @RequiresRoles注解不生效问题
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    

  


}
