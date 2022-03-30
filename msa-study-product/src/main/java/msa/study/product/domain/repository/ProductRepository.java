package msa.study.product.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import msa.study.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
