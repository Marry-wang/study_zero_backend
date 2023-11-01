package com.demo.interceptor;

import com.demo.context.SecurityContextHolder;
import com.demo.jwt.jwtconfig.JwtConfig;
//import com.demo.template.CacheUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HeaderInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String token = request.getHeader("token");
//        String usernameFromToken = JwtConfig.getUsernameFromToken(token);
//        CacheUtil.get(usernameFromToken);
//        SecurityContextHolder.set(request.getHeader("token"),11);
        return true;
    }
}
