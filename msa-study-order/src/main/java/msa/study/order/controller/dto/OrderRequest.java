package msa.study.order.controller.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OrderRequest {
	private List<OrderProductDto> products;
	
	@Schema(description = "주문금액")
	private int orderAmount;
}
