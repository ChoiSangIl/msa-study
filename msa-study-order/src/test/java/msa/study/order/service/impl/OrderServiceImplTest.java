package msa.study.order.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

import msa.study.order.model.entity.OrderEntity;
import msa.study.order.model.entity.repository.OrderRepository;

public class OrderServiceImplTest {

	private RestTemplate template;
	
	private MockRestServiceServer mockServer;
	
    private OrderRepository orderRepository;
	
	@BeforeEach
	public void init() throws URISyntaxException {
		template = new RestTemplate();
		mockServer = MockRestServiceServer.bindTo(template).build();
		orderRepository = mock(OrderRepository.class);
	}
	
	@Test
	@DisplayName("재고차감 api 호출")	
	public void minusStockTest() throws URISyntaxException {
		mockServer.expect(ExpectedCount.once(), 
		          requestTo(new URI("http://localhost:8083/product/stock/order")))
		          .andExpect(method(HttpMethod.PUT))
		          .andRespond(withStatus(HttpStatus.OK)
		          .contentType(MediaType.APPLICATION_JSON)
		          .body("minus stock..."));
		
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<String> entity = new HttpEntity<String>("", headers); 
	    
		ResponseEntity<String> response = template.exchange("http://localhost:8083/product/stock/order", HttpMethod.PUT, entity, String.class);
		assertEquals("minus stock...", response.getBody());
	}
	
	@Test
	@DisplayName("주문서 생성")	
	public void createOrderTest() {
		OrderEntity order = new OrderEntity();
		order.setOrderNumber((long) 1);
		order.setOrderAmount(10000);
		
		doReturn(order).when(orderRepository).save(any());
		OrderEntity saveOrder = orderRepository.save(order);
		
		System.out.println(saveOrder);
		assertThat(saveOrder).isNotNull();
		assertThat(saveOrder.getOrderNumber()).isNotNull();
		assertThat(saveOrder.getOrderAmount()).isNotEqualTo(0);
	}
	
	@Test
	@DisplayName("결제 api 호출")	
	public void pay() throws URISyntaxException {
		mockServer.expect(ExpectedCount.once(), 
		          requestTo(new URI("http://localhost:8082/pay")))
		          .andExpect(method(HttpMethod.POST))
		          .andRespond(withStatus(HttpStatus.OK)
		          .contentType(MediaType.APPLICATION_JSON)
		          .body("pay..."));
		
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<String> entity = new HttpEntity<String>("", headers); 
	    
		ResponseEntity<String> response = template.exchange("http://localhost:8082/pay", HttpMethod.POST, entity, String.class);
		assertEquals("pay...", response.getBody());
	}
}
