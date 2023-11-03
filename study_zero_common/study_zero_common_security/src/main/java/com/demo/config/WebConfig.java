package com.demo.config;

import com.demo.Interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
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
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private TokenInterceptor tokenInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/jwt/login");
//                .excludePathPatterns("/swagger-ui.html")
//                .excludePathPatterns("/swagger-resources/**");
    }
}
