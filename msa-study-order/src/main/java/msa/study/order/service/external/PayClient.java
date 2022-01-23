package msa.study.order.service.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "pay", fallback = PayClientFallback.class) 
public interface PayClient {
	 @PostMapping(value = "/pay", consumes = "application/json")
	 String pay();
}


