package msa.study.product.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import msa.study.product.domain.ProductDetail;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long>{
}
