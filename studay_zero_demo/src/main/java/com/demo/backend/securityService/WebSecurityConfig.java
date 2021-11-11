package com.demo.backend.securityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName WebSecurityConfig
 * @Author 王孟伟
 * @Date 2021/11/8 15:57
 * @Version 1.0
 */
//https://blog.csdn.net/qwe86314/article/details/89509765
//    https://blog.csdn.net/larger5/article/details/81063438
//https://www.cnblogs.com/demingblog/p/10874753.html  入门原理及实践
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

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
//        super.configure(httpSecurity);
        httpSecurity.authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest()//任何其他请求
                .authenticated()//都需要身份认证
                .and()
                .formLogin()//使用表单认证
                .loginProcessingUrl("/security/hello")//配置默认登录入口
                .and()
                .csrf().disable();//关闭跨站请求伪造保护
    }

    @Override  //设置登录用户，不知道为什么 withUser 就报错 springSecurityFilterChain
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
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
