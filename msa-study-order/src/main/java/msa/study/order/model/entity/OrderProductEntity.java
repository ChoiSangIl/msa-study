package msa.study.order.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "ORDER_PRODUCT")
@Getter
@Setter
public class OrderProductEntity extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_PRODUCT_ID")
	private Long orderProductId;
	
	@ManyToOne
	@JoinColumn(name = "ORDER_NUMBER")
	OrderEntity order;
	
	@Column(name="PRODUCT_ID")
	private Long productId;
}
