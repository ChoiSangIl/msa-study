package msa.study.order.controller.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequest {
	private List<OrderProductDto> products;
	
	private int orderAmount;
}
