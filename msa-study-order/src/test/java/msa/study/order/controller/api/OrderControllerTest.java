package msa.study.order.controller.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import msa.study.order.service.OrderService;
import msa.study.order.service.impl.OrderServiceimpl;

public class OrderControllerTest {

	@Test
	void testOrder() {
		OrderService orderService = new OrderServiceimpl();
		OrderController orderController = new OrderController(orderService);
		assertEquals("orderComplete", orderController.order());
	}

}
