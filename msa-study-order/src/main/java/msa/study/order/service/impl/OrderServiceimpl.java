package msa.study.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import msa.study.order.domain.OrderEntity;
import msa.study.order.repository.OrderRepository;
import msa.study.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	private final OrderRepository orderRepository;
	private RestTemplate restTemplate;

	@Autowired 
	public OrderServiceImpl(OrderRepository orderRepository, RestTemplate restTemplate) {
		this.orderRepository = orderRepository;
		this.restTemplate = restTemplate;
	}

	@Override
	public String order() {
		minusStock();
		createOrder();
		kafkaPub();
		return "orderComplete";
	}
	
	private void minusStock() {
		System.out.println("재고차감 process... product 재고 api 호출");
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<String> entity = new HttpEntity<String>("", headers); 
	    ResponseEntity<String> response = restTemplate.exchange("http://localhost:8083/product/stock/order", HttpMethod.PUT, entity, String.class);
	    System.out.println("재고차감 end... response::"+response.getBody());
	}
	
	private void createOrder() {
		System.out.println("주문정보 저장 process...");
		OrderEntity order = new OrderEntity();
		order.setOrderAmount((int)(Math.random()*10000));
		orderRepository.save(order);
		System.out.println("주문정보 저장 process end...");
	}
	
	private void kafkaPub() {
		System.out.println("kafka 주문 정보 publish...");
		System.out.println("kafka 주문 정보 publish end...");
	}

}
