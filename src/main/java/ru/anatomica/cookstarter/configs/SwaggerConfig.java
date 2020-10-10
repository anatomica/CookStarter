package ru.anatomica.cookstarter.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket controllers() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Controllers")
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.anatomica.cookstarter.controllers"))
                .paths(PathSelectors.regex("/api.*"))
                .build();
    }

    @Bean
    public Docket auth() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Authentication")
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.anatomica.cookstarter.controllers"))
                .paths(PathSelectors.regex("/auth"))
                .build();
    }
}
