package msa.study.pay.controller.api;

import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {

	@PostMapping("/pay")
	public String pay() {
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new NoFallbackAvailableException("Boom!", new RuntimeException());
	}
}
