package msa.study.order.service;

import msa.study.order.controller.dto.OrderRequest;
import msa.study.order.controller.dto.OrderResponse;
import msa.study.order.model.entity.OrderEntity;

public interface OrderService {
	OrderResponse orderProcess(OrderRequest orderRequest);
	
	OrderEntity saveOrder(OrderEntity order);
}
