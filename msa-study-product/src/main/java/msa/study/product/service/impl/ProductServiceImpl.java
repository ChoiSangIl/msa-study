package msa.study.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import msa.study.product.controller.api.dto.ProductListRequest;
import msa.study.product.controller.api.dto.ProductListResponse;
import msa.study.product.domain.repository.ProductRepository;
import msa.study.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	private final ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public ProductListResponse getProductList(ProductListRequest request) {
		return ProductListResponse.fromProductList(null);
	}

}
