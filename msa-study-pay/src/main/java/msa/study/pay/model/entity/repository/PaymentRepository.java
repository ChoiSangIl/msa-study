package msa.study.pay.model.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import msa.study.pay.model.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
}
