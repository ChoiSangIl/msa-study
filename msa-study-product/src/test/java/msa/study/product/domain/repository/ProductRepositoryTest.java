package msa.study.product.domain.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import msa.study.product.domain.Product;
import msa.study.product.domain.ProductDetail;

@DataJpaTest
public class ProductRepositoryTest {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductDetailRepository productDetailRepository;
	
	private static final int price = 2000;
	private static final String name = "test상품";
	private static final String thumbnailUrl = "/thumbnail/1.jpg";
	private static final String productDetail = "해당상품 좋아요:)";
	
	@Test
	@DisplayName("상품을 등록 할 수 있다.")
	@Transactional
	@Rollback(false)
	public void create() {
		//given
		Product product = Product.builder()
				.price(price)
				.name(name)
				.thumbnailUrl(thumbnailUrl)
				.build();

		//when
		productRepository.save(product);

		//then
		assertAll(
			()->{ assertEquals(product.getPrice(), price); },
			()->{ assertEquals(product.getName(), name); },
			()->{ assertEquals(product.getThumbnailUrl(), thumbnailUrl); }
		);
	}
	
	@DisplayName("상품과 상품상세 정보를 등록할 수 있다.")
	@Test
	public void createAll() {
		//given
		Product product = Product.builder()
							.price(price)
							.name(name)
							.thumbnailUrl(thumbnailUrl)
							.build();

		ProductDetail detail = ProductDetail.builder()
								.detail(productDetail)
								.product(product)
								.build();
		
		//when
		productRepository.save(product);
		productDetailRepository.save(detail);
		
		//then
		assertAll(
			()->{ assertEquals(product.getPrice(), price); },
			()->{ assertEquals(product.getName(), name); },
			()->{ assertEquals(product.getThumbnailUrl(), thumbnailUrl); },
			()->{ assertEquals(detail.getDetail(), productDetail); }
		);
	}

}
