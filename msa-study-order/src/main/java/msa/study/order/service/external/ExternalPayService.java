package msa.study.order.service.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("pay")
public interface ExternalPayService {
	 @PostMapping(value = "/pay", consumes = "application/json") 
	 String pay();
}
