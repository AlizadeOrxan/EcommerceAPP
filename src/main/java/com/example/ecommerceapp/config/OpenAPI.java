package com.example.ecommerceapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "E-Commerce API", version = "1.0",
                description = "API for Product management project",
                contact = @Contact(
                        name = "Orxan Alizade",
                        url = "https://github.com/AlizadeOrxan" ,
                        email = "o-alizade@mail.ru")
        )
)


@Configuration
public class OpenAPI {
}
