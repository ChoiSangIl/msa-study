package msa.study.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import msa.study.order.domain.OrderEntity;
import msa.study.order.repository.OrderRepository;
import msa.study.order.service.OrderService;
import msa.study.order.service.external.ExternalPayService;
import msa.study.order.service.external.ExternalProductService;

@Service
public class OrderServiceImpl implements OrderService{

	private final OrderRepository orderRepository;
	private EurekaClient discoveryClient;
	private ExternalPayService payService;
	private ExternalProductService productService;
	
	@Autowired 
	public OrderServiceImpl(OrderRepository orderRepository, EurekaClient discoveryClient, ExternalPayService payService, ExternalProductService productService) {
		this.orderRepository = orderRepository;
		this.discoveryClient = discoveryClient;
		this.payService = payService;
		this.productService = productService;
	}

	@Override
	public String order() {
		minusStock();
		createOrder();
		doPay();
		//kafkaPub();
		return "orderComplete";
	}
	
	private void minusStock() {
		System.out.println("재고차감 process... product 재고 api 호출");
		String response = productService.minusStock();
	    System.out.println("재고차감 end... response::"+response);
	}
	
	private void createOrder() {
		System.out.println("주문정보 저장 process...");
		OrderEntity order = new OrderEntity();
		order.setOrderAmount((int)(Math.random()*10000));
		orderRepository.save(order);
		System.out.println("주문정보 저장 process end...");
	}
	
	private void doPay() {
		System.out.println("결제 process... 결제 api 호출");
		String response = payService.pay();
	    System.out.println("결제 end... response::"+response);
	}
	
	private void kafkaPub() {
		System.out.println("kafka 주문 정보 publish...");
		System.out.println("kafka 주문 정보 publish end...");
	}
	
	@Deprecated
	private String getServiceUrl(String serviceName) {
		InstanceInfo instance = discoveryClient.getNextServerFromEureka(serviceName, false);
		System.out.println(serviceName +"::"+instance.getHomePageUrl());
		return instance.getHomePageUrl();
	}

}
