package com.zhang.crm.config;

import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableSwagger2
public class SwaggerConfig {
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2);
    }

}
