/*
 * Copyright (c) 2019 Karumien s.r.o.
 *
 * Karumien s.r.o. is not responsible for defects arising from
 * unauthorized changes to the source code.
 */
package com.karumien.cloud.perf;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger/SpringFox API documentation Configuration.
 *
 * @author <a href="miroslav.svoboda@karumien.com">Miroslav Svoboda</a>
 * @since 1.0, 9. 6. 2019 0:07:58
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * Specification of REST Microservice API v1.0
     * 
     * @return {@link Docket} REST API specification
     */
    @Bean
    public Docket api10() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("perf-service-api-1.0").select()
            .apis(RequestHandlerSelectors.basePackage("com.karumien.cloud.perf.api")).paths(PathSelectors.regex("/test/.*")).build()
            .produces(Collections.singleton(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .apiInfo(new ApiInfoBuilder().version("1.0").title("Performance Service API Documentation")
            .description("Methods for Performance DB Test Service.").build());
    }

}
