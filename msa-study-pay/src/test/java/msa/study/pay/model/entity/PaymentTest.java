package msa.study.pay.model.entity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class PaymentTest {
	private static long orderNumber = 1L;
	private static int orderAmount = 1000;
	private static String cardNumber = "1234-xxxx-xxxx-1234";
	
	@Test
	@DisplayName("Payment 객체를 생성 할 수 있다.")
	public void create() {
		//when
		Payment payment = new Payment(orderNumber, PaymentStatus.COMPLETE, Bank.Hana, cardNumber, orderAmount);
		
		//then
		assertNotNull(payment);
	}
	
	@Test
	@DisplayName("kafka mapper를 이용해 객체를 생성 할 수 있다.")
	public void createFromJson() throws JsonMappingException, JsonProcessingException {
		//given
		String jsonData = "{\"orderNumber\":20,\"paymentType\":\"CARD\",\"bank\":\"Seoul\",\"cardNumber\":\"1234-xxxx-xxxx-345\",\"paymentAmount\":10000}";
		
		//when
		Payment payment = Payment.fromTopic(jsonData);
		
		//then
		assertAll(
			()->assertEquals(payment.getBank(), Bank.Seoul),
			()->assertEquals(payment.getOrderNumber(), 20L)
		);
	}
}
