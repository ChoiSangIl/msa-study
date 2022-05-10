package msa.study.order.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import msa.study.order.controller.dto.Bank;
import msa.study.order.controller.dto.OrderRequest;
import msa.study.order.controller.dto.PaymentType;

@Data
@AllArgsConstructor
public class OrderCreateTopic {
	private long orderNumber;
	
	private PaymentType paymentType;
	
	private Bank bank;
	
	private String cardNumber;
	
	private int paymentAmount;
	
	public static OrderCreateTopic from(Long orderNumber, OrderRequest request) {
		return new OrderCreateTopic(orderNumber, request.getPaymentType(), request.getBank(), request.getCardNumber(), request.getPaymentAmount());
	}
}
