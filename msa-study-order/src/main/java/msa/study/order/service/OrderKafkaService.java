package msa.study.order.service;

import msa.study.order.kafka.OrderCreateTopic;

public interface OrderKafkaService {
	public void sendTopic(OrderCreateTopic topic);
}
