package msa.study.pay.service.impl;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import msa.study.pay.model.entity.Bank;
import msa.study.pay.model.entity.Payment;
import msa.study.pay.model.entity.PaymentStatus;
import msa.study.pay.model.entity.repository.PaymentRepository;
import msa.study.pay.service.PaymentService;

public class PaymentServiceImplTest {

	PaymentRepository paymentRepository = mock(PaymentRepository.class);
	PaymentService paymentService = new PaymentServiceImpl(paymentRepository);
	
	@Test
	@DisplayName("결제 서비스 테스트")
	public void testPay() {
		//given
		Payment payment = new Payment(1L, PaymentStatus.COMPLETE, Bank.Seoul, "1234-xxxx-xxxx-123", 10000);
		
		//when
		paymentService.pay(payment);
	}

}
