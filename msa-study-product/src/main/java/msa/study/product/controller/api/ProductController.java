package msa.study.product.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import msa.study.product.controller.api.dto.ProductListRequest;
import msa.study.product.controller.api.dto.ProductListResponse;
import msa.study.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PutMapping("/stock/order")
	private String minusStock() {
		return "minus stock...";
	}
	
	@GetMapping()
	private ProductListResponse getProductList(ProductListRequest productListRequest) {
		return productService.getProductList(productListRequest);
	}
}