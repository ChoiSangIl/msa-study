package msa.study.pay.service;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PayRequestConsumer {

	  @KafkaListener(topics = "payRequest")
	  public void consume(String message) throws IOException {
		  System.out.println(String.format("Consumed message : %s", message));
	  }
}