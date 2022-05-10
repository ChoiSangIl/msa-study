package msa.study.order.kafka;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import msa.study.order.controller.dto.Bank;
import msa.study.order.controller.dto.OrderRequest;

@DisplayName("Order Create Topic Test")
public class OrderCreateTopicTest {
	
	@Test
	@DisplayName("OrderRequest 객체로부터 자기자신을 생성 할 수 있다.")
	public void createFromOrderEntity() {
		//given
		OrderRequest request = new OrderRequest();
		request.setBank(Bank.Gwangju);
		request.setCardNumber("1234-xxxx-xxxx-123");
		request.setPaymentAmount(10000);
		request.setProducts(null);
		
		//when
		OrderCreateTopic topic = OrderCreateTopic.from(1L, request);
		
		//then
		assertNotNull(topic);
	}

}
