package msa.study.product.service.impl;

import msa.study.product.controller.api.dto.ProductListRequest;
import msa.study.product.controller.api.dto.ProductListResponse;
import msa.study.product.service.ProductService;

public class ProductServiceImpl implements ProductService{

	@Override
	public ProductListResponse getProductList(ProductListRequest request) {
		return ProductListResponse.fromProductList(null);
	}

}
