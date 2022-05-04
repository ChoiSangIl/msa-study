package msa.study.order.service.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("product")
public interface ExternalProductService {
	 @PutMapping(value = "/stock/order", consumes = "application/json") 
	 String minusStock();
}
