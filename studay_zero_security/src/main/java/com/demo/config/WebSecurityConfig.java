package com.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @ClassName WebSecurityConfig
 * @Author 王孟伟
 * @Date 2021/11/8 15:57
 * @Version 1.0
 */
//https://blog.csdn.net/qwe86314/article/details/89509765
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
//        super.configure(httpSecurity);
        httpSecurity.authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest()//任何其他请求
                .authenticated()//都需要身份认证
                .and()
                .formLogin()//使用表单认证
                .loginProcessingUrl("/login")//配置默认登录入口
                .and()
                .csrf().disable();//关闭跨站请求伪造保护
    }
}
