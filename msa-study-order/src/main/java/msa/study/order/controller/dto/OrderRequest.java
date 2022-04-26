package msa.study.order.controller.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class OrderRequest {
	private List<OrderProductDto> products = new ArrayList<OrderProductDto>();
	
	private int orderAmount;
}
