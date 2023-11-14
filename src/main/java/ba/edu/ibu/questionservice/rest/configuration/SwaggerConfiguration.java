package ba.edu.ibu.questionservice.rest.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "IBU Web Enginering", version = "1.0.0", description = "Web Engineering Backend Application", contact = @Contact(name = "Web Engineering", email = "becir.isakovic@ibu.edu.ba")), servers = {@Server(url = "/", description = "Default Server URL")})

public class SwaggerConfiguration {

}
