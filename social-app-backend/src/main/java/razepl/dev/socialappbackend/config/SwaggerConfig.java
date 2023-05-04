package razepl.dev.socialappbackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Szymon Półtorak",
                        email = "szymonpotorak@gmail.com",
                        url = "https://github.com/szymonpoltorak"
                ),
                description = "Api documentation for Social App academic project.",
                title = "Api specification - Szymon Półtorak",
                version = "0.3.2",
                license = @License(
                        name = "Apache-2.0 license",
                        url = "https://www.apache.org/licenses/"
                )
        ),
        servers = {
                @Server(
                        description = "Backend environment",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Frontend environment",
                        url = "http://localhost:4200"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "Jwt Auth"
                )
        }
)
@SecurityScheme(
        name = "Jwt Auth",
        description = "Jwt auth using Bearer token",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
}
