package msa.study.order.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import msa.study.order.controller.dto.OrderRequest;
import msa.study.order.controller.dto.OrderResponse;
import msa.study.order.service.OrderService;

@RestController
@RequiredArgsConstructor
@Tag(name = "주문관련 API")
public class OrderController {
	private final OrderService orderService;
	
	@PostMapping
	@Operation(summary = "주문서 생성 요청")
	public OrderResponse orders(@RequestBody OrderRequest orderRequest) {
		return orderService.orderProcess(orderRequest);
	}
	
	@GetMapping
	@Operation(summary = "주문 리스트 조회")
	public String getOrders() {
		return "test...";
	}
}
