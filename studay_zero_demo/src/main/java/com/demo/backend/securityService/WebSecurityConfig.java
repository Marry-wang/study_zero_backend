package com.demo.backend.securityService;

import com.demo.backend.dto.Result;
import com.demo.backend.dto.ResultCode;
import com.demo.backend.dto.ResultFactory;
import com.demo.backend.securityService.SecurityHandler.LoginSuccessHandler;
import com.demo.config.CorssFilter;
import com.demo.filter.SecurityTokenFilter;
import com.demo.jwt.Interceptor.TokenInterceptor;
import com.demo.jwt.jwtconfig.JwtConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName WebSecurityConfig
 * @Author 王孟伟
 * @Date 2021/11/8 15:57
 * @Version 1.0
 */
//https://blog.csdn.net/qwe86314/article/details/89509765
//    https://blog.csdn.net/larger5/article/details/81063438
//https://www.cnblogs.com/demingblog/p/10874753.html  入门原理及实践
//    https://blog.csdn.net/DX_dixi/article/details/108344849
//    https://blog.csdn.net/qq_42033495/article/details/106617448  handler
@Configuration
//开启spring Security 功能
@EnableWebSecurity
//添加@EnableGlobalMethodSecurity注解开启Spring方法级安全
//prePostEnabled属性决定Spring Security的前注解是否可用@PreAuthorize,@PostAuthorize等注解,设置为true
//@EnableGlobalMethodSecurity(proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private CorssFilter corsFilter;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Autowired
    private SecurityTokenFilter securityTokenFilter;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{

//        super.configure(httpSecurity);
        httpSecurity

                .cors().and()
                .csrf().disable()//关闭跨站请求伪造保护
                .authorizeRequests()
                //authenticated()  需要登陆后进行访问
                .antMatchers("/login").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest()//任何其他请求
                .authenticated()//都需要身份认证
//                .permitAll()
                .and()
                .formLogin()//使用表单认证
//                .successForwardUrl()
//                .failureForwardUrl()
//                .loginProcessingUrl("/login")//配置默认登录入口
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        Result ok = ResultFactory.buildSuccess(jwtConfig.createToken(((User)authentication.getPrincipal()).getUsername()));
                        System.out.println(authentication.getName());
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write(new ObjectMapper().writeValueAsString(ok));
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                        Result error = ResultFactory.buidResult(ResultCode.FAIL,e+"","");
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write(new ObjectMapper().writeValueAsString(error));
                        out.flush();
                        out.close();
                    }
                })
                // 防止iframe 造成跨域
//                .and()
//                .headers()
//                .frameOptions()
//                .disable()
//                .and().rememberMe().key("ADMIN") // 启用 remember me

//                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                //前后端分离采用JWT 不需要session
                // 不创建会话
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
        httpSecurity
                .addFilterBefore(securityTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(corsFilter,UsernamePasswordAuthenticationFilter.class)
        ;
    }

    @Override  //访问白名单
    public void configure(WebSecurity webSecurity) throws Exception{
        webSecurity.ignoring()
//                .antMatchers("/")
//                .antMatchers("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**")
//                .antMatchers("/login")
//                .antMatchers("/jwt/getUserInfo")
//                .antMatchers("/security/info")
//                .antMatchers("/security/hello")
//                .antMatchers("/base/person")
        ;
    }

    @Override  //设置登录用户，不知道为什么 withUser 就报错 springSecurityFilterChain
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
//        auth.inMemoryAuthentication().withUser("user").password("1111")
//                .roles("ADMIN").and();
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("{noop}123456");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        //NoOpPasswordEncoder（这个就是不加密）
        return NoOpPasswordEncoder.getInstance();
        //输入的密码为加密处理的
//        return new BCryptPasswordEncoder();
    }
}
