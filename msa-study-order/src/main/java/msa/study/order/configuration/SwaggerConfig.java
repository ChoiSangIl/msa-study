package msa.study.order.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	private static final String GATEWAY_URL = "http://localhost:8080/order-service";

	@Bean
	public OpenAPI springOpenAPI() {
		Server server = new Server();
		server.setUrl(GATEWAY_URL);
		List<Server> list = new ArrayList<Server>();
		list.add(server);

		return new OpenAPI()
				.info(new Info().title("msa-study-order").description("msa-study-order sample application").version("v0.0.1"))
				.servers(list);
	}

}
