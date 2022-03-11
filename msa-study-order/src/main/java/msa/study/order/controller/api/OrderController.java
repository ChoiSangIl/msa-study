package msa.study.order.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import msa.study.order.model.entity.OrderEntity;
import msa.study.order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	private final OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService){
		this.orderService = orderService;
	}
	
	@PostMapping("")
	public String orders(@RequestBody OrderEntity order) {
		return orderService.orders();
	}
}
