package msa.study.product.controller.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ProductControllerTest {
	
	MockMvc mockMvc;

	@BeforeEach
	private void init() {
		ProductController productController = new ProductController();
		this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}
	
	@Test
	public void testMinusStock() throws Exception {
		MvcResult mvcResult = mockMvc.perform(put("/product/stock/order"))
		.andExpect(status().isOk())
		.andDo(print())
		.andReturn();
		
		assertEquals("minus stock...", mvcResult.getResponse().getContentAsString());
	}
}
