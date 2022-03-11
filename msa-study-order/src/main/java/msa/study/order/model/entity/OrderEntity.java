package msa.study.order.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

@Entity(name="ORDERS")
@Getter
@Setter
public class OrderEntity extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ORDER_NUMBER")
	private Long orderNumber;
	
	@Column(name="ORDER_AMOUNT", nullable = false)
	private int orderAmount;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private OrderStatus status;
	
	/*
	@OneToMany
	List<OrderProductEntity> orderProductList;
	*/

	@Version
	private long version;
}
