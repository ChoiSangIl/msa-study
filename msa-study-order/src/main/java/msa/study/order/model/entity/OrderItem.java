package msa.study.order.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "ORDER_ITEM")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_PRODUCT_ID")
	private Long orderItemId;
	
	@ManyToOne
	@JoinColumn(name = "ORDER_NUMBER")
	@JsonBackReference
	OrderEntity order;
	
	@Column(name="PRODUCT_ID")
	private Long productId;
	
	@Column(name="PRICE")
	private int price;
	
	@Column(name="QUANTITY")
	private int quantity;
	
	public OrderItem(Long productId, OrderEntity order, int price, int quantity) {
		this.validationNonZero(quantity);
		this.validationNonZero(price);
		this.productId = orderItemId;
		this.order = order;
		this.price = price;
		this.quantity = quantity;
	}
	
	public void setOrder(OrderEntity order) {
		if(this.order != null) {
			this.order.getOrderProductList().remove(this);
		}
		this.order = order;
		order.getOrderProductList().add(this);
	}
	
	private void validationNonZero(int number) {
		if(number <= 0) {
			throw new NullPointerException("0보다 작습니다.");
		}
	}
}
