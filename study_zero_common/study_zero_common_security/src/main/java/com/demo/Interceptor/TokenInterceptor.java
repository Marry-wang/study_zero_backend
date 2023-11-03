package com.demo.Interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @ClassName TokenInterceptor
 * @Author 王孟伟
 * @Date 2021/11/2 9:10
 * @Version 1.0
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String url = request.getRequestURI();
//        if(url.equals("/demo/")){
//            return true;
//        }
//        if(url.contains("/login")){
//            return true;
//        }
//        if(url.contains("/swagger")){
//            return true;
//        }
//        if(url.contains("/error")){
//            return true;
//        }
//        if(url.contains("/csrf")){
//            return true;
//        }
//
//
//        /**
//         * token 验证
//         */
//        String token = request.getHeader(jwtConfig.getHeader());
//        if(StringUtils.isEmpty(token)){
//            token = request.getParameter(jwtConfig.getHeader());
//        }
//        if(StringUtils.isEmpty(token)){
//            throw new SignatureException(jwtConfig.getHeader()+"不能为空");
//        }
//        Claims claims = null;
//        try {
//            claims = jwtConfig.getTokenClaim(token);
//            if(claims == null || jwtConfig.isTokenExpired(claims.getExpiration())){
//                throw new SignatureException(jwtConfig.getHeader()+"失效,请重新登录");
//            }
//        }catch (Exception e){
//            throw new SignatureException(jwtConfig.getHeader()+"失效,请重新登录");
//        }
//        request.setAttribute("identityId",claims.getSubject());
//        return true;
//    }

}
