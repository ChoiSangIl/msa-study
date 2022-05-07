package msa.study.order.configuration;


import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	private static final String GATEWAY_URL = "http://localhost:8080/orders";

	@Bean
	public GroupedOpenApi publicApi() {
		Server server = new Server();
		server.setUrl(GATEWAY_URL);
		List<Server> list = new ArrayList<Server>();
		list.add(server);
		
		return GroupedOpenApi.builder().group("msa-order").pathsToMatch("/**").addOpenApiCustomiser(openApi -> openApi.servers(list)).build();
	}
}
