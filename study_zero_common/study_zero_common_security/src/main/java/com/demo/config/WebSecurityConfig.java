package com.demo.config;

import com.demo.interceptor.HeaderInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebConfig
 * @Author 王孟伟
 * @Date 2021/11/2 9:43
 * @Version 1.0
 */
@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {
    @Bean
    public HeaderInterceptor getHaHandlerInterceptor() {
        return new HeaderInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getHaHandlerInterceptor())
                .addPathPatterns("/**");

    }
}
