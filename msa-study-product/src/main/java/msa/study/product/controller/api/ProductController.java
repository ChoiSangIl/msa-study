package msa.study.product.controller.api;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

	@PutMapping("/stock/order")
	private String minusStock() {
		return "minus stock...";
	}
}