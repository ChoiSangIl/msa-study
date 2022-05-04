package msa.study.product.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 상품 검색 VO
 * @author Choi Sang Il
 *
 */
@Getter
@Setter
public class ProductListRequest {

	@Schema(description = "상품명")
	private String productName;
	
}
