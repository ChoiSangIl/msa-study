package msa.study.pay.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import msa.study.pay.model.entity.Payment;

@Service
@Slf4j
public class PaymentListener {

	@KafkaListener(topics = "order-create")
	public void listen(ConsumerRecord<?, ?> record) {
		log.info(String.format("Consumed message : %s", record));
		try {
			Payment payment = Payment.fromTopic(record.value().toString());
			log.info(payment.toString());
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
