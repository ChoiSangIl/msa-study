package msa.study.product.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import msa.study.product.domain.Product;
import msa.study.product.domain.ProductDetail;
import msa.study.product.domain.repository.ProductDetailRepository;
import msa.study.product.domain.repository.ProductRepository;

@Component
public class DumyProductCreator implements CommandLineRunner{

	private final ProductRepository productRepository;
	
	@Autowired
	private ProductDetailRepository productDetailRepository;
	
	private static final int price = 2000;
	private static final String name = "test상품";
	private static final String thumbnailUrl = "/thumbnail/1.jpg";
	private static final String productDetail = "해당상품 좋아요:)";

	@Autowired
	public DumyProductCreator(ProductRepository productRepository, ProductDetailRepository productDetailRepository) {
		this.productRepository = productRepository;
		this.productDetailRepository = productDetailRepository;
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		/*
		 * 동시에 서비스가 동시에 올라가면 테스트 상품이 여러개 등록 될 수 있다. (실제 서비스에서는 주석처리하거나, dev, real로 나눠서 처리 해야함)
		 */
		if(productRepository.findAll().isEmpty()) {
			Product product = Product.builder()
					.price(price)
					.name(name)
					.thumbnailUrl(thumbnailUrl)
					.build();

			ProductDetail detail = ProductDetail.builder()
								.detail(productDetail)
								.product(product)
								.build();
			
			productRepository.save(product);
			productDetailRepository.save(detail);
			
		}
	}

}
