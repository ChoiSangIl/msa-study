package msa.study.product.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProductEntityTest {
	
	private static final long productId = 1L;
	private static final int price = 2000;
	private static final String name = "test상품";
	
	
	@Test
	@DisplayName("상품객체를 생성할 수 있다.")
	public void createProduct() {
		ProductEntity product = new ProductEntity.ProductEntityBuilder()
										.id(productId)
										.price(price)
										.name(name)
										.build();
		assertAll(
			()->{ assertEquals(product.getId(), productId); },
			()->{ assertEquals(product.getPrice(), price); },
			()->{ assertEquals(product.getName(), name); }
		);
		
	}
}
