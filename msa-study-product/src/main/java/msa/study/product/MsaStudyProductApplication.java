package msa.study.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsaStudyProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsaStudyProductApplication.class, args);
	}

}
