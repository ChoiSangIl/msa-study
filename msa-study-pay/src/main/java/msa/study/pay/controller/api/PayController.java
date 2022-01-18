package msa.study.pay.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {

	@PostMapping("/pay")
	public String pay() {
		System.out.println("pay...");
		return "pay...";
	}
}
