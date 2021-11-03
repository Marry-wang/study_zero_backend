package com.demo.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Swagger2Config
 * @Author 王孟伟
 * @Date 2021/10/14 15:08
 * @Version 1.0
 *
 * https://www.cnblogs.com/gaohanghang/p/13308205.html
 */
@Configuration
@EnableSwagger2
@PropertySource({"classpath:application-dev.yml"})
public class Swagger2Config {

    //是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
    @Value("${swagger.enable}")
    private Boolean enable;

    @Bean
    public Docket createRestApi() {
        List<Parameter> parameters = new ArrayList<>();

        parameters.add(new ParameterBuilder()
                .name("token")  //Token 以及Authorization 为自定义的参数，session保存的名字是哪个就可以写成那个
                .description("token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build()
        );
        return new Docket(DocumentationType.SWAGGER_2)   //主要api配置机制初始化为swagger规范2.0
                .enable(enable)
                .apiInfo(apiInfo())
                .select()
                // 对所有该包下的Api进行监控，如果想要监控所有的话可以改成any()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                // 对所有路径进行扫描
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful API")  // 标题
                .description("rest api 文档构建利器")  // 描述信息
                .termsOfServiceUrl("http://blog.csdn.net/itguangit")  //服务网址
                .contact("itguang")  // 联系方式
                .version("1.0") //版本号
                .build();
    }



}
