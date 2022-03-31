package msa.study.product.controller.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
import org.springframework.test.web.servlet.MvcResult;

import msa.study.product.controller.api.dto.ProductListResponse;
import msa.study.product.domain.Product;
import msa.study.product.service.ProductService;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ProductService productService;
	
	private static final long productId = 1L;
	private static final int price = 2000;
	private static final String name = "test상품";
	private static final String thumbnailUrl = "/thumbnail/1.jpg";
	
	@Test
	public void testMinusStock() throws Exception {
		MvcResult mvcResult = mockMvc.perform(put("/product/stock/order"))
		.andExpect(status().isOk())
		.andDo(print())
		.andReturn();
		
		assertEquals("minus stock...", mvcResult.getResponse().getContentAsString());
	}

	@Test
	@DisplayName("상품을 조회할 수 있다.")
	public void testGetProductList() throws Exception {
		//given
		Product product = Product.builder()
				.id(productId)
				.price(price)
				.name(name)
				.thumbnailUrl(thumbnailUrl)
				.build();
		
		List<Product> products = new ArrayList<Product>();
		products.add(product);
		
		ProductListResponse productListResponse = ProductListResponse.fromProductList(products);
		
		doReturn(productListResponse).when(productService).getProductList(any());
		
		//when
		mockMvc.perform(get("/product")
			.contentType(MediaType.APPLICATION_JSON)
			.queryParam("productName", "상품")
		)
		
		//then
		.andExpect(status().isOk())
		.andExpect(jsonPath("products[0].price").value("2000"))
		.andDo(print());
	}
}
