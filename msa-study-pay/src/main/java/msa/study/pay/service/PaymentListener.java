package msa.study.pay.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import msa.study.pay.model.entity.Payment;

@Service
public class PaymentListener {

	  @KafkaListener(topics = "order-create")
	  public void listen(ConsumerRecord<?, ?> record) { 
		  System.out.println(String.format("Consumed message : %s", record));
		  record.value();
		  ObjectMapper mapper = new ObjectMapper();
	  }
}
