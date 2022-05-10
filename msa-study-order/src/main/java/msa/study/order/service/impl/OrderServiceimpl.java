package msa.study.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import msa.study.order.controller.dto.OrderRequest;
import msa.study.order.controller.dto.OrderResponse;
import msa.study.order.kafka.OrderCreateTopic;
import msa.study.order.model.entity.OrderEntity;
import msa.study.order.model.entity.repository.OrderProductRepository;
import msa.study.order.model.entity.repository.OrderRepository;
import msa.study.order.service.OrderKafkaService;
import msa.study.order.service.OrderService;
import msa.study.order.service.external.ExternalProductService;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService, OrderKafkaService{

	private final OrderRepository orderRepository;
	private final OrderProductRepository orderProductRepository;
	private ExternalProductService productService;
	private KafkaTemplate<String, OrderCreateTopic> kafkaTemplate;
	
	@Autowired 
	public OrderServiceImpl(OrderRepository orderRepository, OrderProductRepository orderProductRepository, ExternalProductService productService, KafkaTemplate<String, OrderCreateTopic> kafkaTemplate) {
		this.orderRepository = orderRepository;
		this.productService = productService;
		this.kafkaTemplate = kafkaTemplate;
		this.orderProductRepository = orderProductRepository;
	}

	@Override
	@Transactional
	public OrderResponse orderProcess(OrderRequest orderRequest) {
		checkStock();
		OrderEntity order = orderRepository.save(OrderEntity.from(orderRequest));
		sendTopic(OrderCreateTopic.from(order.getOrderNumber(), orderRequest));
		return OrderResponse.of(order.getOrderNumber(), order.getOrderAmount());
	}
	
	@Override
	@Deprecated
	public OrderEntity saveOrder(OrderEntity order) {
		orderRepository.save(order);
		orderProductRepository.saveAll(order.getOrderProductList());
		return order;
	}
	
	private void checkStock() {
		productService.minusStock();
	}
	
	@Override
	public void sendTopic(OrderCreateTopic topic) {
		log.info("주문서 발행... " + topic.toString());
		kafkaTemplate.send("order-create", topic);
	}
	
}
