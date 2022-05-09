package msa.study.order.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import msa.study.order.controller.dto.OrderRequest;

@Entity(name="ORDERS")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
	
	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	@Cascade(CascadeType.PERSIST)
	List<OrderProductEntity> orderProductList = new ArrayList<OrderProductEntity>();;

	@Version
	private long version;
	
	public OrderEntity(int orderAmount, OrderStatus status) {
		this.orderAmount = orderAmount;
		this.status = status;
	}
	
	private OrderEntity(OrderRequest orderRequest) {
		this.orderAmount = orderRequest.getOrderAmount();
		this.status = OrderStatus.PAYMENT_READY;
		orderRequest.getProducts().forEach((obj)->{
			OrderProductEntity product = new OrderProductEntity(obj.getProductId(), this, obj.getUnitPrice(), obj.getQuantity());
			this.orderProductList.add(product);
		});
	}
	
	public void addProduct(OrderProductEntity product) {
		orderProductList.add(product);
		product.setOrder(this);
	}
	
	public static OrderEntity from(OrderRequest orderRequest) {
		return new OrderEntity(orderRequest);
	}
}
