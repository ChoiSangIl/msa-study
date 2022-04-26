package msa.study.order.controller.dto;

import lombok.Data;

@Data
public class OrderResponse {
	private Long orderNumber;
	
	private int orderAmount;
	
	private OrderResponse(Long orderNumber, int orderAmount) {
		this.orderNumber = orderNumber;
		this.orderAmount = orderAmount;
	}
	
	public static OrderResponse of(Long orderNumber, int orderAmount) {
		return new OrderResponse(orderNumber, orderAmount);
	}
}
