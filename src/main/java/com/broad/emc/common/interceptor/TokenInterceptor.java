package com.broad.emc.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.broad.emc.common.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        String token = request.getHeader("token"); //前端vue将token添加在请求头中
        System.out.println("token=========="+token);
        if (token != null) {
            boolean result = TokenUtil.verify(token);
            if (result) {
                //通过拦截器
                return true;
            }
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        JSONObject json = new JSONObject();
        try {
            //认证失败，未通过拦截器
            json.put("code", "403");
            response.getWriter().append(json.toJSONString());
        } catch (Exception e) {
            json.put("code", "500");
            response.getWriter().append(json.toJSONString());
            log.error("认证异常，{}", e);
        }
        return false;
    }
}
