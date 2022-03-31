package msa.study.product.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import msa.study.product.domain.common.BaseEntity;

@Entity
@Getter
@ToString
public class ProductDetail extends BaseEntity{
	
	@Id
	private long id;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "PRODUCT_NO")
	private Product product;
	
	@Lob
	private String detail;

	@Builder
	public ProductDetail(long id, Product product, String detail) {
		this.id = id;
		this.product = product;
		this.detail = detail;
	}
	
}
