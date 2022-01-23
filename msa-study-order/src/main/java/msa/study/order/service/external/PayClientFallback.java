package msa.study.order.service.external;

import org.springframework.stereotype.Component;

@Component
public class PayClientFallback implements PayClient{

	@Override
	public String pay() {
		return "fail...";
	}

}
