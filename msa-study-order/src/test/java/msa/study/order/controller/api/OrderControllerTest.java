package msa.study.order.controller.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import msa.study.order.controller.dto.OrderProductDto;
import msa.study.order.controller.dto.OrderRequest;
import msa.study.order.controller.dto.OrderResponse;
import msa.study.order.service.OrderService;

@WebMvcTest(controllers = OrderController.class)
@DisplayName("주문 컨트롤러 테스트")
public class OrderControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@MockBean
	OrderService orderService;
	 
	@Test
	@DisplayName("주문 API를 호출할 수 있다.")
	void testOrder() throws Exception {
		//given
		OrderRequest orderRequest = new OrderRequest();
		OrderResponse orderResponse = OrderResponse.of(1L, 1);
		OrderProductDto orderProductDto = new OrderProductDto();
		List<OrderProductDto> orderProducts = new ArrayList<OrderProductDto>();
		orderProductDto.setProductId(1L);
		orderProductDto.setProductName("테스트상품");
		orderProductDto.setQuantity(10);
		orderProducts.add(orderProductDto);
		orderRequest.setProducts(orderProducts);
		orderRequest.setOrderAmount(10000);
		orderResponse.setOrderNumber(1L);
		doReturn(orderResponse).when(orderService).orderProcess(any());
		
		//when
		mockMvc.perform(
				post("/orders")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(orderRequest))
		)
		
		//then
		.andExpect(status().isOk())
		.andExpect(jsonPath("orderNumber").value(1));
	}

	@Test
	@DisplayName("자신의 주문서 정보를 가져올 수 있다.")
	public void testGetOrders() {
	}

}
