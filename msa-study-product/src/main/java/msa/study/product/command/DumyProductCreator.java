package msa.study.product.command;

import java.util.ArrayList;
import java.util.List;

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
	
	private static final String name = "test상품";
	private static final String thumbnailUrl = "/thumbnail/";
	private static final String productDetail = "상품 상세";

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
			
			final int dumyProductCount = 20;
			List<Product> products = new ArrayList<Product>();
			List<ProductDetail> details = new ArrayList<ProductDetail>();
			
			for (int i=1; i<= dumyProductCount; i++) {
				Product product = Product.builder()
						.price((int)(Math.random() * 99000) + 1000)
						.name(name + i)
						.thumbnailUrl(thumbnailUrl + i + ".jpg")
						.build();

				ProductDetail detail = ProductDetail.builder()
									.detail(i + " " + productDetail)
									.product(product)
									.build();
				
				products.add(product);
				details.add(detail);
			}
			
			productRepository.saveAll(products);
			productDetailRepository.saveAll(details);
		}
	}

}
