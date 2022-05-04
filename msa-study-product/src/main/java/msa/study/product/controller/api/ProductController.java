package msa.study.product.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import msa.study.product.controller.dto.ProductListRequest;
import msa.study.product.controller.dto.ProductListResponse;
import msa.study.product.service.ProductService;

@RestController
@RequestMapping("/product")
@Tag(name = "상품관련 API")
public class ProductController {

	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PutMapping("/stock/order")
	@Operation(summary = "재고차감")
	private String minusStock() {
		return "minus stock...";
	}
	
	@GetMapping("/search")
	@Operation(summary = "상품검색")
	private ProductListResponse getProductList(ProductListRequest productListRequest) {
		return productService.getProductList(productListRequest);
	}
}