package msa.study.order.service.external;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import msa.study.order.service.externalService.ExternalPayService;

@SpringBootTest
public class ExternalPayServiceTest {

	@Autowired
	ExternalPayService payService;
	
	@DisplayName("feign 호출 테스트")
	public void payService() {
		System.out.println(payService.pay());
	}
}
