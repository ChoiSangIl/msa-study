package msa.study.order.controller.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import msa.study.order.service.OrderService;

@WebMvcTest(controllers = OrderController.class)
public class OrderControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	OrderService orderService;
	 
	@Test
	@DisplayName("주문 API를 호출할 수 있다.")
	void testOrder() throws Exception {
		//given
		doReturn("orderComplete").when(orderService).orders();
		
		//when
		MvcResult mvcResult = mockMvc.perform(
				post("/orders")
		)
		
		//then
		.andExpect(status().isOk())
		.andDo(print())
		.andReturn();
		assertEquals("orderComplete", mvcResult.getResponse().getContentAsString());
	}

}
