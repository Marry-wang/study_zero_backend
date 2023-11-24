package com.demo.Interceptor;

import com.alibaba.fastjson.JSONObject;
import com.demo.config.JwtConfig;
import com.demo.context.SecurityContextHolder;
import com.demo.enums.BaseResultEnum;
import com.demo.exception.BaseException;
import com.demo.template.CacheUtil;
import io.netty.util.internal.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangmengwei
 */
@Component
public class HeaderInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入方法之前");
        String token = request.getHeader("token");
        if (StringUtil.isNullOrEmpty(token)) {
            //TODO 做一个白名单处理
            System.out.println(request.getRequestURL() + "全路径 http://127.0.0.1:10002/system/login");
            System.out.println(request.getServletPath() + "请求路径/system/login");
            if ("/system/login".equals(request.getServletPath()) || "/error".equals(request.getServletPath())) {
                return true;
            } else {
                throw new BaseException(BaseResultEnum.TOKENNOTUSER);
            }
        } else {
            String cacheToken = CacheUtil.get(token);
            if (StringUtil.isNullOrEmpty(cacheToken)) {
                throw new BaseException(BaseResultEnum.TOKENERROR);
            }

            String message = JwtConfig.getUsernameFromToken(cacheToken);
            SecurityContextHolder.setLocalMap(JSONObject.parseObject(message));
        }


//        String usernameFromToken = JwtConfig.getUsernameFromToken(token);
//        CacheUtil.get(usernameFromToken);
//        SecurityContextHolder.set(request.getHeader("token"),11);
        return true;
    }
}
