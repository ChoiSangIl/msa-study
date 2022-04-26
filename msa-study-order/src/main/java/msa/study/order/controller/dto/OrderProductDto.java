package msa.study.order.controller.dto;

import lombok.Data;

@Data
public class OrderProductDto {
	private Long productId;
	
	private String productName;
	
	private int unitPrice;
	
	private int quantity;
}
