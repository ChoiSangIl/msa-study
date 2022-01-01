package msa.study.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import msa.study.order.domain.OrderEntity;
import msa.study.order.repository.OrderRepository;
import msa.study.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	private final OrderRepository orderRepository;

	@Autowired 
	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public String order() {
		OrderEntity order = new OrderEntity();
		order.setOrderAmount(1000);
		orderRepository.save(order);
		
		System.out.println("재고차감 process...");
		System.out.println("주문정보 저장 process...");
		System.out.println("kafka 주문 정보 publish...");
		return "orderComplete";
	}

}
