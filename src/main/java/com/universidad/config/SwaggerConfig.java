package com.universidad.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Sistema Registro Universitario",
                version = "1.0",
                description = "Documentación de la API del sistema universitario"
        )
)
public class SwaggerConfig {}
