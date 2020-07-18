package com.barrett.aialertservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: smartsite-master
 * @description: Configuration of swagger UI
 * @author: Barrett
 * @create: 2020-07-02 10:25
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalOperationParameters(globalOperationParameters())// 全局参数
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/.*"))// 监控所有路径
                .build();
    }
    private List<Parameter> globalOperationParameters(){
        ParameterBuilder builder = new ParameterBuilder();
        Parameter parameter = builder.name("token").description("登录接口返回的token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build();
        ArrayList<Parameter> parameters = new ArrayList<>();
        parameters.add(parameter);
        return parameters;
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("智慧AI后台API文档集合")
                .description("Barrett Zhou Copyright 2000-2030 (C) All Rights Reserved.")
                .termsOfServiceUrl("https://github.com/SPGBarrett")
                .contact(new Contact("Barrett",null,"spg_barrett@live.cn"))
                .version("1.0")
                .build();
    }
}

