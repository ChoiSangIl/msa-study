package msa.study.pay.model.entity.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import msa.study.pay.model.entity.Bank;
import msa.study.pay.model.entity.Payment;
import msa.study.pay.model.entity.PaymentStatus;

@DataJpaTest
public class PaymentRepositoryTest{
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Test
	@DisplayName("결제 정보를 저장 할 수 있다.")
	public void save() {
		//given
		Payment payment = new Payment(1L, PaymentStatus.COMPLETE, Bank.Seoul, "1234-xxxx-xxxx-123", 10000);
		
		//when
		paymentRepository.save(payment);
		
		//then
		assertNotNull(payment.getId());
	}

}
