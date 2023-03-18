package com.cuidl.pip.core.config;

import com.google.common.base.Predicates;
import io.swagger.models.Swagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author cuidl
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket adminSwagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .select()
                .build();
    }
}
