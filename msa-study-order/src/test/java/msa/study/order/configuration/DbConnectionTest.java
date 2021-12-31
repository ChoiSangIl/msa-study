package msa.study.order.configuration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DbConnectionTest {

	@Autowired
	DataSource dataSource;
	
	@Test
	public void testConnection() {
		assertNotNull(dataSource);
		System.out.println(dataSource);
	}
}
