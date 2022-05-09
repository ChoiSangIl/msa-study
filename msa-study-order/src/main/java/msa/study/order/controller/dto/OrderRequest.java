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

	@Schema(description = "결제종류")
	private PaymentType paymentType;
	
	@Schema(description = "결제은행")
	private Bank bank;
	
	@Schema(description = "카드번호", example = "1234-xxxx-xxxx-345")
	private String cardNumber;
	
	@Schema(description = "결제금액", example = "10000")
	private int paymentAmount;
}
