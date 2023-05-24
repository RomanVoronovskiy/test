package ru.voronovskii.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Тестовый сервис")
                        .description(" Предназначен для управления статьями ")
                        .version("v1.0.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Книга, которая была взята для example")
                        .url("https://ru.wikipedia.org/wiki/%D0%9A%D0%BE%D0%BB%D1%8B%D0%"
                                + "B1%D0%B5%D0%BB%D1%8C_%D0%B4%D0%BB%D1%8F_%D0%BA%D0%BE%D1%88%D0%BA%D0%B8"));
    }
}
