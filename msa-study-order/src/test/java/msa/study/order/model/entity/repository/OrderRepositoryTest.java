package msa.study.order.model.entity.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import msa.study.order.model.entity.OrderEntity;
import msa.study.order.model.entity.OrderProductEntity;
import msa.study.order.model.entity.OrderStatus;

@DataJpaTest
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
	public void 주문_단방향_다대일_테스트() {
		//given
		OrderEntity order = new OrderEntity();
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
