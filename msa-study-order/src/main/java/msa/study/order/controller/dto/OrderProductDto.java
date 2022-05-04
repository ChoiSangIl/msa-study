package msa.study.order.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OrderProductDto {
	
	@Schema(description = "주문 상품 리스트", example = "1")
	private Long productId;
	
	@Schema(description = "상품명", example = "test 상품1")
	private String productName;

	@Schema(description = "상품단가", example = "1000")
	private int unitPrice;

	@Schema(description = "구매수량", example = "10")
	private int quantity;
	
}
