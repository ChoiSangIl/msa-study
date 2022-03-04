package msa.study.order.configuration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
public class KafkaProducerConfigTest {

	@Autowired
	KafkaTemplate<?, String> kafkaTemplate;
	
	@Test
	public void kafkaConnectTest() {
		assertNotNull(kafkaTemplate);
		this.kafkaTemplate.send("test", "123");
	}
}
