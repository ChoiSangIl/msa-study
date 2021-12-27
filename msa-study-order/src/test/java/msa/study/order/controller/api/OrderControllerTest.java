package msa.study.order.controller.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import msa.study.order.service.OrderService;

public class OrderControllerTest {
	
	/**
	 * controller 호출이 잘 되는지 테스트 할 수 있는 케이스를 추가 작성해야함.
	 * 
	 */
	@Test
	void testOrder() {
		OrderService orderService = mock(OrderService.class);
		doReturn("orderComplete").when(orderService).order();
		OrderController orderController = new OrderController(orderService);
		assertEquals("orderComplete", orderController.order());
	}

}
