package msa.study.pay.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Table(name="PAYMENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
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
	@Column(name = "PAYMENT_TYPE")
	private PaymentType paymentType;
	
	@Enumerated
	private Bank bank;

	@Column(name = "CARD_NUMBER")
	private String cardNumber;

	@Column(name = "PAY_AMOUNT")
	private int paymentAmount;

	public Payment(long orderNumber, PaymentStatus status, Bank bank, String cardNumber, int amount) {
		this.orderNumber = orderNumber;
		this.status = status;
		this.bank = bank;
		this.cardNumber = cardNumber;
		this.paymentAmount = amount;
	}
	
	public static Payment fromTopic(String jsonData) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(jsonData, Payment.class);
	}
	
	public void setPaymentStatus(PaymentStatus status) {
		this.status = status;
	}
	
}

