package msa.study.order.model.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderProductEntityTest {
	
	@Test
	@DisplayName("객체를 생성 할 수 있다.")
	void create() {
		//when
		OrderProductEntity orderProduct = new OrderProductEntity(1L, new OrderEntity(), 1000, 10);
		
		//then
		assertThat(orderProduct).isNotNull();
	}
	
	@Test
	@DisplayName("필수값이 존재하지 않으면, 오류가난다.")
	void createValidation() {
		Assertions.assertThrows(NullPointerException.class, ()->{
			new OrderProductEntity(1L, new OrderEntity(), 0, 10);
		});
	}
}
