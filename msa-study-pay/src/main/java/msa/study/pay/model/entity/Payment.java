package msa.study.pay.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name="PAYMENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "ORDER_NUMBER")
	private long orderNumber;
	
	@Enumerated
	@Column(name = "PAYMENT_STATUS")
	private PaymentStatus status;
	
	@Enumerated
	private Bank bank;

	@Column(name = "CARD_NUMBER")
	private String cardNumber;

	@Column(name = "PAY_AMOUNT")
	private int amount;

	public Payment(long orderNumber, PaymentStatus status, Bank bank, String cardNumber, int amount) {
		this.orderNumber = orderNumber;
		this.status = status;
		this.bank = bank;
		this.cardNumber = cardNumber;
		this.amount = amount;
	}
}

