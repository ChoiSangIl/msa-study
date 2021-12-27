package msa.study.order.service.impl;

import org.springframework.stereotype.Service;

import msa.study.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Override
	public String order() {
		System.out.println("재고차감 process...");
		System.out.println("주문정보 저장 process...");
		System.out.println("kafka 주문 정보 publish...");
		return "orderComplete";
	}

}
