package msa.study.pay.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {

	@GetMapping("/pay")
	public String pay() {
		return "pay...";
	}
}
