package msa.study.product.service;

import msa.study.product.controller.api.dto.ProductListRequest;
import msa.study.product.controller.api.dto.ProductListResponse;

public interface ProductService {
	/**
	 * 상품 리스트 가져오기
	 * @param request
	 * @return
	 */
	ProductListResponse getProductList(ProductListRequest request); 
}
