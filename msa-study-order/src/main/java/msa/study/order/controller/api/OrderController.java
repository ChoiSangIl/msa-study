package msa.study.order.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import msa.study.order.service.OrderService;

@RestController
public class OrderController {
	private final OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService){
		this.orderService = orderService;
	}
	
	@GetMapping("/order")
	public String order() {
		return orderService.order();
	}
}
