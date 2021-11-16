package com.demo.filter;

import com.demo.jwt.jwtconfig.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * @ClassName TokenFilter
 * @Author 王孟伟
 * @Date 2021/11/16 13:49
 * @Version 1.0
 */

/**
 * 自定义过滤器，该过滤器在security配置中被设置在security拦截之前处罚
 */
@Component
public class SecurityTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtConfig jwtConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();
        String token = request.getHeader(jwtConfig.getHeader());
        String userNameFromToken ="";
        if(!url.contains("/login")){
            /**
             * token 验证
             */

            if(StringUtils.isEmpty(token)){
                token = request.getParameter(jwtConfig.getHeader());
            }
            if(StringUtils.isEmpty(token)){
                throw new SignatureException(jwtConfig.getHeader()+"不能为空");
            }
            Claims claims = null;
            try {
                claims = jwtConfig.getTokenClaim(token);
                if(claims == null || jwtConfig.isTokenExpired(claims.getExpiration())){
                    throw new SignatureException(jwtConfig.getHeader()+"失效,请重新登录");
                }
            }catch (Exception e){
                throw new SignatureException(jwtConfig.getHeader()+"失效,请重新登录");
            }
            userNameFromToken = jwtConfig.getUsernameFromToken(token);
        }


        //设置角色
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority =new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ADMIN";
            }
        };
        grantedAuthorities.add(grantedAuthority);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userNameFromToken, null, grantedAuthorities);
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);

//        request.setAttribute("identityId",claims.getSubject());
    }
}
