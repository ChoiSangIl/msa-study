package msa.study.order.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import msa.study.order.controller.dto.OrderRequest;
import msa.study.order.controller.dto.OrderResponse;
import msa.study.order.service.OrderService;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
	private final OrderService orderService;
	
	@PostMapping
	public OrderResponse orders(@RequestBody OrderRequest orderRequest) {
		return orderService.orderProcess(orderRequest);
	}
	
	@GetMapping
	public String getOrders() {
		return "test...";
	}
}
