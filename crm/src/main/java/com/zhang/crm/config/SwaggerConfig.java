package com.zhang.crm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.context.support.RequestHandledEvent;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Component
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docketEmp() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("用户").apiInfo(apiInfo())
                /* .enable(true)//通过enable()配置是否启用swagger，如果是false，swagger将不能在浏览器中访问了
                 *//*忽略参数Integer*//*
                .ignoredParameterTypes(Integer.class)
                .select().apis(RequestHandlerSelectors.basePackage("com.zhang.crm.controller"))
                .build();*/
                .select()
                // RequestHandlerSelectors配置如何扫描接口
                .apis(RequestHandlerSelectors.basePackage("com.zhang.crm.controller"))
                /*扫描/saveEmp开头的requestMapping路径接口*/
                .paths(PathSelectors.ant("/emp/**")).build();
    }
    @Bean
    public Docket docketDept() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("部门").apiInfo(apiInfo())
                .select()
                // RequestHandlerSelectors配置如何扫描接口
                .apis(RequestHandlerSelectors.basePackage("com.zhang.crm.controller"))
                /*扫描/saveEmp开头的requestMapping路径接口*/
                .paths(PathSelectors.ant("/dept/**")).build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Tomcat", "www.baidu.com", "892826800@qq.com");
        return new ApiInfo("swagger学习",
                "学习配置Sagger",
                "v1.0",
                "http://www.baidu.com",
                contact,
                "Apach 2.0 许可",
                "许可链接", new ArrayList<>());
    }
}
