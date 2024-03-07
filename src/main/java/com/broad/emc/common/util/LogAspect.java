package com.broad.emc.common.util;

import com.broad.emc.module.entity.Admin;
import com.broad.emc.module.entity.HtLog;
import com.broad.emc.module.service.LogService;
import com.broad.emc.common.annotation.MyArchivesLog;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.util.*;

/**
 * 日志AOP类
 */

@Aspect
@Component
@CrossOrigin
public class LogAspect {
    @Resource
    private LogService logService;


    private static final Logger logger = LoggerFactory.getLogger(MyArchivesLog.class);

    @Pointcut("@annotation(com.broad.emc.common.annotation.MyArchivesLog)")
    public void controllerAspect() {
    }

    /**
     * 后置通知
     * 方法调用后触发 , 记录正常操作
     *
     * @param joinPoint
     * @throws ClassNotFoundException
     */
    @AfterReturning(value = "controllerAspect()", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) throws ClassNotFoundException, UnknownHostException, UnknownHostException {
        //用户名
        String userName = getUSerMsg().getAccount();
        // 用户IP
        //String ip = InetAddress.getLocalHost().getHostAddress();
        // 控制器名
        String targetName = getMethodDesc(joinPoint).getController();
        // 方法名
        String methodName = getMethodDesc(joinPoint).getMethod();
        // 操作说明
        String operteContent = getMethodDesc(joinPoint).getOperateContent();
        //String parameters = Arrays.toString(removeServletObjects(joinPoint.getArgs()));
        String returnValueStr = (returnValue == null ? "null" : returnValue.toString());
        System.out.println("操作者:" + userName);
        System.out.println("操作说明:" + operteContent);
        System.out.println("方法参数:" + Arrays.toString(joinPoint.getArgs()));
        System.out.println("方法返回值:" + (returnValue == null ? "null" : returnValue.toString()));

        HtLog log = new HtLog();

        //log.setParameters(parameters);
        log.setReturnValue(returnValueStr);
        //log.setIp(ip);
        log.setOperateContent(operteContent);
        log.setMethod(methodName);
        log.setController(targetName);
        log.setOperateTime(new Date());
        log.setUserName(userName);
        logService.insertLog(log);
    }





    /**
     * 发生异常，走此方法
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void AfterThrowing(JoinPoint joinPoint, Throwable e) {
        try {
            //操作者姓名
            String userName = getUSerMsg().getAccount();
            // 用户IP
            //String ip = InetAddress.getLocalHost().getHostAddress();
            // 控制器名
            String targetName = getMethodDesc(joinPoint).getController();
            // 方法名
            String methodName = getMethodDesc(joinPoint).getMethod();
            // 操作说明
            String operteContent = getMethodDesc(joinPoint).getOperateContent();

            HtLog log = new HtLog();
            String exMsg = e.getCause().toString();
            System.out.println("操作者:" + userName);
            System.out.println("操作说明:" + operteContent);
            System.out.println("方法参数:" + Arrays.toString(joinPoint.getArgs()));
            System.out.println("操作有异常:" + e.getCause().toString());
            if (exMsg != null) {
                int type = 2;
                //log.setIp(ip);
                log.setOperateContent(operteContent);
                //log.setParameters(parameters);
                log.setMethod(methodName);
                log.setController(targetName);
                log.setUserName(userName);
                //logService.insertLog(log);
            }
        } catch (Exception e1) {
            logger.error(e1.getMessage());
        }
    }

    /**
     * 获取 注解中对方法的描述
     *
     * @return
     * @throws ClassNotFoundException
     */
    public static HtLog getMethodDesc(JoinPoint joinPoint) throws ClassNotFoundException {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String operteContent = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    //操作说明
                    operteContent = method.getAnnotation(MyArchivesLog.class).operteContent();
                    break;
                }
            }
        }
        HtLog htLog=new HtLog();
        htLog.setController(targetName);
        htLog.setMethod(methodName);
        htLog.setOperateContent(operteContent);
        return htLog;
    }

    /**
     * 得到用户信息
     * @return
     */
    public static Admin getUSerMsg() {
//        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username="";
//        String password="";
//        if (principal!=null){
//            username = principal.getUsername();
//            password = principal.getPassword();
//        }


        // 获取当前Subject对象
        Subject subject = SecurityUtils.getSubject();
        // 获取当前用户的信息
        Admin admin = (Admin) subject.getPrincipal();
        System.out.println("当前用户信息：" + admin);
        return admin;
    }

    //获取当前登录用户的token  可以通过这个
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }


    /*去掉方法参数的servlet对象*/
    public static Object[] removeServletObjects(Object[] objects) {
        List list = Arrays.asList(objects);
        list = new ArrayList(list);
        for (int i = 0; i < list.size(); i++) {
            Object object = list.get(i);
            if (object instanceof HttpServletResponse || object instanceof HttpServletRequest) {
                list.remove(object);
                i--;
            }
        }
        System.out.println(list);
        objects = list.toArray();
        return objects;
    }

}

