package com.ejc.mirage.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    OpenAPI customOpenAPI(@Value("${application.version}") String appVersion) {
        return new OpenAPI()
            .info(new Info().title("EJC - Mirage API").description("A webservice with dummy endpoints.").version(appVersion));
    }
}
