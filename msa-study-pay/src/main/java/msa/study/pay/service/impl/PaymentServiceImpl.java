package msa.study.pay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import msa.study.pay.model.entity.Payment;
import msa.study.pay.model.entity.PaymentStatus;
import msa.study.pay.model.entity.repository.PaymentRepository;
import msa.study.pay.service.PaymentService;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService{
	
	private final PaymentRepository paymentRepository;

	@Autowired
	public PaymentServiceImpl(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	@Override
	public void pay(Payment payment) {
		//결제로직...
		payment.setPaymentStatus(PaymentStatus.COMPLETE);
		paymentRepository.save(payment);
		log.info(payment.toString());
	}
}
