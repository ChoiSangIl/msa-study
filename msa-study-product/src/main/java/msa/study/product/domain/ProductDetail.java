package msa.study.product.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Setter;

@Entity
@Setter
public class ProductDetail {
	
	@Id
	private long id;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "PRODUCT_NO")
	private Product product;
}
