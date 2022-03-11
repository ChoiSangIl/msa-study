package msa.study.order.model.entity.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import msa.study.order.model.entity.OrderEntity;
import msa.study.order.model.entity.OrderProductEntity;
import msa.study.order.model.entity.OrderStatus;

@SpringBootTest( properties = {"spring.config.location=classpath:application-test.properties"} )
public class OrderRepositoryTest {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderProductRepository orderProductRepository;
	
	@BeforeEach
	@DisplayName("객체 생성")
	public void init() {
	}

	@Test
	@DisplayName("order Entity 저장, 조회")
	@Transactional
	@Rollback(value = false)
	public void orderEntitySaveAndSearch() {
		//given
		OrderEntity order;
		assertNotNull(orderRepository);
		order = new OrderEntity();
		order.setOrderAmount((int)(Math.random()*10000));
		order.setCreateAt(LocalDateTime.now());
		order.setStatus(OrderStatus.PAYMENT_COMPLETE);
		
		//when
		orderRepository.save(order);
		
		//then
		assertNotNull(order.getOrderNumber());
		assertNotNull(order.getOrderAmount());
		assertNotNull(order.getCreateAt());
		
		//given
		OrderProductEntity orderProduct = new OrderProductEntity();
		orderProduct.setOrder(order);
		orderProduct.setProductId((long) 1);
		
		//when
		orderProductRepository.save(orderProduct);
		
		//then
		assertNotNull(orderProduct.getOrderProductId());
	}
}
