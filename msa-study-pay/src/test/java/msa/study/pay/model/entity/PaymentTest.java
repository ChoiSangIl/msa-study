package msa.study.pay.model.entity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
