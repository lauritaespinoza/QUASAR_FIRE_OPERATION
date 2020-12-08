package com.quasar.rest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info().title("Quasar Fire Operation - Open API 3.0 Reference").version("v1")
                .description("This API allows know the spaceship position since its satellite's distances and decodes the secret messages received from them"));
    }
}


