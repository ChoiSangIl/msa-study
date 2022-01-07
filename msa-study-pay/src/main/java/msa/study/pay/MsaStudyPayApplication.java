package msa.study.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsaStudyPayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsaStudyPayApplication.class, args);
	}

}
