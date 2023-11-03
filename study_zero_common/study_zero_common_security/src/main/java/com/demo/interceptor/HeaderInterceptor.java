package com.demo.interceptor;

import com.demo.template.CacheUtil;
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
        if (token.isEmpty()) {
            System.out.println(request.getRequestURL() + "1111111111");
            System.out.println(request.getServletPath() + "22222222");
        }
        String cacheToken = CacheUtil.get(token);
        if (cacheToken.isEmpty()) {

        }

//        String usernameFromToken = JwtConfig.getUsernameFromToken(token);
//        CacheUtil.get(usernameFromToken);
//        SecurityContextHolder.set(request.getHeader("token"),11);
        return true;
    }
}
