package msa.study.order.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import msa.study.order.controller.dto.OrderResponse;
import msa.study.order.model.entity.OrderEntity;
import msa.study.order.model.entity.OrderProductEntity;
import msa.study.order.model.entity.OrderStatus;
import msa.study.order.model.entity.repository.OrderProductRepository;
import msa.study.order.model.entity.repository.OrderRepository;
import msa.study.order.service.OrderService;
import msa.study.order.service.external.ExternalProductService;
import msa.study.order.service.external.PayClient;

@Service
public class OrderServiceImpl implements OrderService{

	private final OrderRepository orderRepository;
	private final OrderProductRepository orderProductRepository;
	private ExternalProductService productService;
	private KafkaTemplate<String, OrderEntity> kafkaTemplate;
	
	@Autowired 
	public OrderServiceImpl(OrderRepository orderRepository, OrderProductRepository orderProductRepository, ExternalProductService productService, KafkaTemplate<String, OrderEntity> kafkaTemplate) {
		this.orderRepository = orderRepository;
		this.productService = productService;
		this.kafkaTemplate = kafkaTemplate;
		this.orderProductRepository = orderProductRepository;
	}

	@Override
	@Transactional
	public OrderResponse createOrder() {
		minusStock();
		payRequest(saveOrderInfo());
		
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setOrderNumber(1L);
		return orderResponse;
	}
	
	private void minusStock() {
		productService.minusStock();
	}
	
	/**
	 * 결제 정보 생성
	 * @return
	 */
	private OrderEntity saveOrderInfo() {
		OrderEntity order = new OrderEntity();
		order.setOrderAmount((int)(Math.random()*10000));
		order.setCreateAt(LocalDateTime.now());
		order.setStatus(OrderStatus.PAYMENT_READY);

		OrderProductEntity orderProduct = new OrderProductEntity();
		orderProduct.setOrderProductId((long)1);
		orderProduct.setOrder(order);
		
		orderRepository.save(order);
		orderProductRepository.save(orderProduct);
		return order;
	}
	
	/**
	 * kafka 결제 메세지 발행
	 */
	private void payRequest(OrderEntity order) {
		System.out.println(order.toString());
		kafkaTemplate.send("payRequest", order);
	}

}
