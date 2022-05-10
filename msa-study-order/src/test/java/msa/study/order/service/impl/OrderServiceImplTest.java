package msa.study.order.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import msa.study.order.controller.dto.OrderProductDto;
import msa.study.order.controller.dto.OrderRequest;
import msa.study.order.controller.dto.OrderResponse;
import msa.study.order.kafka.OrderCreateTopic;
import msa.study.order.model.entity.OrderEntity;
import msa.study.order.model.entity.repository.OrderProductRepository;
import msa.study.order.model.entity.repository.OrderRepository;
import msa.study.order.service.OrderKafkaService;
import msa.study.order.service.OrderService;
import msa.study.order.service.external.ExternalProductService;

@DisplayName("주문 서비스 구현체 테스트")
public class OrderServiceImplTest {

	private RestTemplate template;
	
	private MockRestServiceServer mockServer;
	
    private OrderRepository orderRepository = mock(OrderRepository.class);
    private OrderProductRepository orderProductRepository = mock(OrderProductRepository.class);
    private ExternalProductService productService = mock(ExternalProductService.class);
    private KafkaTemplate<String, OrderCreateTopic> kafkaTemplate = mock(KafkaTemplate.class);
	private OrderService orderService = new OrderServiceImpl(orderRepository, orderProductRepository, productService, kafkaTemplate);
	private OrderKafkaService orderKafkaService = new OrderServiceImpl(orderRepository, orderProductRepository, productService, kafkaTemplate); 
	private OrderRequest orderRequest;
	
	@BeforeEach
	public void init() throws URISyntaxException {
		template = new RestTemplate();
		mockServer = MockRestServiceServer.bindTo(template).build();
		createOrderRequestDumyData();
	}
	
	public void createOrderRequestDumyData() {
		orderRequest = new OrderRequest();
		OrderProductDto orderProductDto = new OrderProductDto();
		List<OrderProductDto> orderProducts = new ArrayList<OrderProductDto>();
		orderProductDto.setProductId(1L);
		orderProductDto.setProductName("테스트상품");
		orderProductDto.setQuantity(10);
		orderProductDto.setUnitPrice(1000);
		orderProducts.add(orderProductDto);
		orderRequest.setProducts(orderProducts);
		orderRequest.setPaymentAmount(1000);
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
	public void saveOrderTest() {
		//given
		OrderEntity order = OrderEntity.from(orderRequest);
		doReturn(order).when(orderRepository).save(any());
		doReturn(order.getOrderProductList()).when(orderProductRepository).save(any());
		
		//when
		OrderEntity saveOrder = orderService.saveOrder(order);
		
		//then
		assertAll(
			()->assertEquals(saveOrder.getOrderAmount(), 1000)
		);
	}
	
	@Test
	@DisplayName("order create topic 전송 test")
	public void sendTopicTest() {
		OrderCreateTopic topic = OrderCreateTopic.from(1L, orderRequest);
		//when
		orderKafkaService.sendTopic(topic);
	}
	
	@Test
	@DisplayName("주문서 생성 process test")
	public void orderProcessTest() {
		//given
		OrderEntity order = OrderEntity.from(orderRequest);
		order.setOrderNumber(1L);
		doReturn(order).when(orderRepository).save(any());
		
		//when
		OrderResponse response = orderService.orderProcess(orderRequest);
		
		//then
		assertAll(
			()->assertEquals(response.getOrderAmount(), order.getOrderAmount()),
			()->assertEquals(response.getOrderNumber(), order.getOrderNumber())
		);
	}
	
}
