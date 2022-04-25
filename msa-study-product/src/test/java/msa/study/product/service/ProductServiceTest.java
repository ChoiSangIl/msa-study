package msa.study.product.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import msa.study.product.controller.dto.ProductListRequest;
import msa.study.product.controller.dto.ProductListResponse;
import msa.study.product.domain.Product;
import msa.study.product.domain.repository.ProductRepository;
import msa.study.product.service.impl.ProductServiceImpl;

public class ProductServiceTest {
	
	private final ProductRepository productRepository = mock(ProductRepository.class);
	private final ProductService productService = new ProductServiceImpl(productRepository);
	
	private static final long productId = 1L;
	private static final int price = 2000;
	private static final String name = "test상품";
	private static final String thumbnailUrl = "/thumbnail/1.jpg";
	
	@Test
	@DisplayName("상품을 조회할 수 있다.")
	public void testGetProductList() {
		//given
		Product product = Product.builder()
				.id(productId)
				.price(price)
				.name(name)
				.thumbnailUrl(thumbnailUrl)
				.build();
		List<Product> products = new ArrayList<Product>();
		products.add(product);
		ProductListRequest productListRequest = new ProductListRequest();
		doReturn(products).when(productRepository).findAll();
		
		//when
		ProductListResponse productListResponse = productService.getProductList(productListRequest);
		
		//then
		assertAll(
				()->{ assertEquals(productListResponse.getProducts().get(0).getId(), productId); },
				()->{ assertEquals(productListResponse.getProducts().get(0).getPrice(), price); },
				()->{ assertEquals(productListResponse.getProducts().get(0).getName(), name); },
				()->{ assertEquals(productListResponse.getProducts().get(0).getThumbnailUrl(), thumbnailUrl); }
		);
	}
}
