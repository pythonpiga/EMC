package com.broad.emc.common.exception;

import com.broad.emc.module.vo.ReturnData;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 自定义权限异常类
 * 异常处理
 */
@ControllerAdvice
public class PermisiionException {
    /**
     * 无权限异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public ReturnData unauthorizedException(Exception e){
        ReturnData ret=ReturnData.getSuccessData();
        ret.setCode("203");
        ret.setMsg("对不起，您的权限不足");
        return ret;

    }

    /**
     * 权限认证异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public ReturnData unAuthorization(Exception e){
        ReturnData ret=ReturnData.getSuccessData();
        ret.setCode("203");
        ret.setMsg("对不起，权限认证失败");
        return ret;

    }

}

