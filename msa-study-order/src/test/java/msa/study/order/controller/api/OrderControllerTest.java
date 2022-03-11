package msa.study.order.controller.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import msa.study.order.service.OrderService;

public class OrderControllerTest {
	
	private MockMvc mockMvc;
	
	OrderService orderService;
	OrderController orderController;
	
	@BeforeEach
	private void init() {
		this.orderService = mock(OrderService.class); 
		doReturn("orderComplete").when(orderService).orders();
		this.orderController = new OrderController(orderService);
		this.mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
	}
	 
	@Test
	void testOrder() throws Exception {
		assertNotNull(mockMvc);
		
		MvcResult mvcResult = mockMvc.perform(post("/orders"))
			.andExpect(status().isOk())
			.andDo(print())
			.andReturn();

		assertEquals("orderComplete", mvcResult.getResponse().getContentAsString());
	}

}
