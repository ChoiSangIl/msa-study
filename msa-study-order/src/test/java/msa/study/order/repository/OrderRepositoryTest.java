package msa.study.order.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import msa.study.order.domain.OrderEntity;

@SpringBootTest( properties = {"spring.config.location=classpath:application-test.properties"} )
public class OrderRepositoryTest {
	
	@Autowired
	OrderRepository orderRepository;
	
	OrderEntity order;
	
	@BeforeEach
	@DisplayName("객체 생성 여부 체크")
	public void init() {
		assertNotNull(orderRepository);
		order = new OrderEntity();
		order.setOrderAmount((int)(Math.random()*10000));
		order.setCreateAt(LocalDateTime.now());
	}

	@Test
	@DisplayName("order Entity 저장, 조회")
	@Transactional
	public void orderEntitySaveAndSearch() {
		orderRepository.save(order);
		OrderEntity searchOrder = orderRepository.getById((long) order.getOrderNumber());
		assertNotNull(searchOrder.getOrderNumber());
		assertNotNull(searchOrder.getOrderAmount());
		assertNotNull(searchOrder.getCreateAt());
	}
}
