package msa.study.order.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

public class OrderServiceImplTest {

	private RestTemplate template;
	
	private MockRestServiceServer mockServer;
	
	@BeforeEach
	public void init() throws URISyntaxException {
		template = new RestTemplate();
		mockServer = MockRestServiceServer.bindTo(template).build();
		mockServer.expect(ExpectedCount.once(), 
		          requestTo(new URI("http://localhost:8083/product/stock/order")))
		          .andExpect(method(HttpMethod.PUT))
		          .andRespond(withStatus(HttpStatus.OK)
		          .contentType(MediaType.APPLICATION_JSON)
		          .body("minus stock..."));            
	}
	
	@Test
	public void testOrder() throws URISyntaxException {
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<String> entity = new HttpEntity<String>("", headers); 
	    
		ResponseEntity<String> response = template.exchange("http://localhost:8083/product/stock/order", HttpMethod.PUT, entity, String.class);
		assertEquals("minus stock...", response.getBody());
	}

}
