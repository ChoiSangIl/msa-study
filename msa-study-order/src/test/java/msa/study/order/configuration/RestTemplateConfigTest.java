package msa.study.order.configuration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class RestTemplateConfigTest {
	
    @Autowired
	private RestTemplate restTemplate;

	@Test
	public void testRestTemplate() {
		assertNotNull(restTemplate);
	}
}
