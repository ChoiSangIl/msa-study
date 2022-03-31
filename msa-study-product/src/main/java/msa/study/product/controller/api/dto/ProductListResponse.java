package msa.study.product.controller.api.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import msa.study.product.domain.Product;

@Getter	
public class ProductListResponse {
	List<ProductDto> products;
	
	private ProductListResponse() {
		throw new IllegalAccessError();
	}
	
	private ProductListResponse(List<ProductDto> products) {
		this.products = products;
	}
	
	public static ProductListResponse fromProductList(List<Product> products) {
		List<ProductDto> results = new ArrayList<ProductDto>();
		products.forEach(product->{
			results.add(ProductDto.fromProduct(product));
		});
		return new ProductListResponse(results); 
	}
}
