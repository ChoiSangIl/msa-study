package msa.study.order.service.impl;

import org.springframework.stereotype.Service;

import msa.study.order.service.OrderService;

@Service
public class OrderServiceimpl implements OrderService{
	
	/**
	 *
	 */
	@Override
	public String order() {
		return "orderComplete";
	}

}
