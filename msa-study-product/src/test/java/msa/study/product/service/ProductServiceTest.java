package msa.study.product.service;

import static org.mockito.Mockito.mock;

import msa.study.product.controller.api.dto.ProductListRequest;
import msa.study.product.domain.repository.ProductRepository;
import msa.study.product.service.impl.ProductServiceImpl;

public class ProductServiceTest {
	
	private final ProductRepository productRepository = mock(ProductRepository.class);
	private final ProductService productService = new ProductServiceImpl(productRepository);
	
	public void testGetProductList() {
		ProductListRequest productListRequest = new ProductListRequest();
	}
}
