package msa.study.order.model.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import msa.study.order.model.entity.OrderItem;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderItem, Long>{

}

