package msa.study.order.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import msa.study.order.model.entity.OrderEntity;
import msa.study.order.model.entity.OrderStatus;
import msa.study.order.repository.OrderRepository;
import msa.study.order.service.OrderService;
import msa.study.order.service.external.ExternalProductService;
import msa.study.order.service.external.PayClient;

@Service
public class OrderServiceImpl implements OrderService{

	private final OrderRepository orderRepository;
	private EurekaClient discoveryClient;
	private PayClient payService;
	private ExternalProductService productService;
	private KafkaTemplate<String, OrderEntity> kafkaTemplate;
	
	
	@Autowired 
	public OrderServiceImpl(OrderRepository orderRepository, EurekaClient discoveryClient, PayClient payService, ExternalProductService productService, KafkaTemplate<String, OrderEntity> kafkaTemplate) {
		this.orderRepository = orderRepository;
		this.discoveryClient = discoveryClient;
		this.payService = payService;
		this.productService = productService;
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public String orders() {
		minusStock();
		OrderEntity order = saveOrderInfo();
		payRequest(order);
		return "orderComplete";
	}
	
	private void minusStock() {
		System.out.println("재고차감 process... product 재고 api 호출");
		String response = productService.minusStock();
	    System.out.println("재고차감 end... response::"+response);
	}
	
	private OrderEntity saveOrderInfo() {
		System.out.println("주문정보 저장 process...");
		OrderEntity order = new OrderEntity();
		order.setOrderAmount((int)(Math.random()*10000));
		order.setCreateAt(LocalDateTime.now());
		order.setStatus(OrderStatus.PAYMENT_READY);
		orderRepository.save(order);
		System.out.println("주문정보 저장 process end...");
		return order;
	}
	
	/**
	 * kafka 결제 메세지 발행
	 */
	private void payRequest(OrderEntity order) {
		System.out.println(order.toString());
		kafkaTemplate.send("payRequest", order);
	}
	
	/**
	 * api 호출 방식에서 메시지큐 방식으로 변경
	 */
	@Deprecated
	private void doPay() {
		System.out.println("결제 process... 결제 api 호출");
		String response = payService.pay();
	    System.out.println("결제 end... response::"+response);
	}
	
	/**
	 * 기존 유레카로 api 정보를 가져오는 부분을 feign으로 변경 
	 * @param serviceName
	 * @return
	 */
	@Deprecated
	private String getServiceUrl(String serviceName) {
		InstanceInfo instance = discoveryClient.getNextServerFromEureka(serviceName, false);
		System.out.println(serviceName +"::"+instance.getHomePageUrl());
		return instance.getHomePageUrl();
	}

}
