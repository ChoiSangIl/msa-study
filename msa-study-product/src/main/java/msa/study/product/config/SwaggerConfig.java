package msa.study.product.config;

import java.util.List;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {
	
	private static final String GATEWAY_URL = "http://localhost:8080";

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("msa-study-product").pathsToMatch("/**").build();
	}

	/**
	 * swagger 호출 api를 gateway로 설정해주기 위함
	 * 실제 운영환경에서는 운영환경의 GATEWAY 주소로 변경되어야함
	 * @return
	 */
	@Bean
	public OpenAPI customOpenAPI() {
		Server server = new Server();
		server.setUrl(GATEWAY_URL);
		return new OpenAPI().servers(List.of(server));
	}

}
