package msa.study.order.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.NonFinal;

@Entity(name = "ORDER_PRODUCT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
	
	@Column(name="PRICE")
	private int price;
	
	@Column(name="QUANTITY")
	private int quantity;
	
	public OrderProductEntity(Long productId, OrderEntity order, int price, int quantity) {
		this.validationNonZero(quantity);
		this.validationNonZero(price);
		this.productId = orderProductId;
		this.order = order;
		this.price = price;
		this.quantity = quantity;
	}
	
	private void validationNonZero(int number) {
		if(number <= 0) {
			throw new NullPointerException("0보다 작습니다.");
		}
	}
}
