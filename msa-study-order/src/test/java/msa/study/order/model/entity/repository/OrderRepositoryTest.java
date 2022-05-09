package msa.study.order.model.entity.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import msa.study.order.model.entity.OrderEntity;
import msa.study.order.model.entity.OrderItem;
import msa.study.order.model.entity.OrderStatus;

@DataJpaTest
public class OrderRepositoryTest {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderProductRepository orderProductRepository;
	
	private OrderEntity order;
	private OrderItem orderProduct;
	
	@BeforeEach
	@DisplayName("객체 생성")
	public void init() {
		//given
		order = new OrderEntity((int)(Math.random()*10000), OrderStatus.PAYMENT_COMPLETE);
		order.setCreateAt(LocalDateTime.now());
		
		orderProduct = new OrderItem(1L, order, 1000, 10);
	}

	@Test
	@DisplayName("order Entity 저장, 조회")
	@Transactional
	public void 주문_단방향_다대일_테스트() {
		//when
		orderRepository.save(order);
		
		//then
		assertNotNull(order.getOrderNumber());
		assertNotNull(order.getOrderAmount());
		assertNotNull(order.getCreateAt());
		
		//when
		orderProductRepository.save(orderProduct);
		
		//then
		assertNotNull(orderProduct.getOrderItemId());
	}
	
	@Test
	@DisplayName("order entity 영속성 전이 Test")
	public void orderSaveTest() {
		//when
		order.addProduct(orderProduct);
		orderRepository.save(order);
		
		//then
		assertNotNull(order.getOrderNumber());
		assertNotNull(orderProduct.getOrderItemId());
		
	}
}
