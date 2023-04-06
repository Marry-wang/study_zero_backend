package com.demo.backend.securityService.SecurityHandler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName LoginSuccessHandler
 * @Author 王孟伟
 * @Date 2021/11/17 10:32
 * @Version 1.0
 */
@Component
public class LoginSuccessHandler  {

//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
//        System.out.println(authentication.getName());
//        super.onAuthenticationSuccess(request, response, authentication);
//    }
}
