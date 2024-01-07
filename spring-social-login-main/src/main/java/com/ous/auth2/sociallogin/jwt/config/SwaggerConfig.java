package com.ous.auth2.sociallogin.jwt.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Ousmane Sangary",
                        email = "sangary7683@yahoo.com",
                        url = "https://ousmane.hashnode.dev/find-the-peak-element-in-a-mountain-array-java"),
                description = "Open APi documentation for JWT",
                title = "OpenAPi specification - Ousmane",
                version = "1.0",
                license = @License(name = "MIT License", url = "https://ousmane.hashnode.dev/find-the-peak-element-in-a-mountain-array-java"),
                termsOfService = "Use but be careful"
        ),
        servers = {
                @Server(
                        description = "LOCAL ENVIRONMENT",
                        url = "https://ousmane.hashnode.dev/find-the-peak-element-in-a-mountain-array-java"
                ),
                @Server(
                        description = "PRODUCTION ENVIRONMENT",
                        url = "https://ousmane.hashnode.dev/find-the-peak-element-in-a-mountain-array-java"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER)
public class SwaggerConfig {
}
