package com.sdcxv.bs;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket testApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sdcxv.bs"))
                .paths(PathSelectors.any())
                .build();
    }



    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("Demo EQ Server API",//大标题
                "No DB version",//小标题
                "1.1",//版本
                "NO terms of service",
                "xudong.liu@tymphany.com",//作者
                "Tymphany",//链接显示文字
                "http://www.tymphany.com/"//网站链接
        );

        return apiInfo;
    }
}
