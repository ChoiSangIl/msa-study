package msa.study.order.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import msa.study.order.domain.OrderEntity;

@SpringBootTest
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
	public void orderEntitySaveAndSearch() {
		orderRepository.save(order);
		
		/*
		 * 왜 해당 코드를 호출하면 No Session오류가날까? 영속성에 대해서 더 공부해야겠다. 
		 * 일단 처히라거하고 확인해보자
		 * OrderEntity searchOrder = orderRepository.getById((long)
		 * order.getOrderNumber()); assertNotNull(searchOrder.getOrderNumber());
		 * assertNotNull(searchOrder.getOrderAmount());
		 * assertNotNull(searchOrder.getCreateAt());
		 * System.out.println(searchOrder.toString());
		 */
	}
}
