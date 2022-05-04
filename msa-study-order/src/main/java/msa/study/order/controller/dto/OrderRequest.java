package msa.study.order.controller.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * 주문 요청 Object
 * @author Choi Sang Il
 *
 */
@Data
public class OrderRequest {
	
	@Schema(description = "주문 상품 리스트")
	private List<OrderProductDto> products;
	
	@Schema(description = "주문금액", example = "10000")
	private int orderAmount;
	
}
