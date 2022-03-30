package msa.study.product.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="PRODUCT")
@Getter
public class Product{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PRODUCT_NO")
	private long id;
	
	private String name;
	
	private int price;
	
	@Column(name="THUMBNAIL_URL")
	private String thumbnailUrl;
	
	@OneToOne(mappedBy = "product")
	@Cascade(CascadeType.ALL)
	private ProductDetail detail;

	@Builder
	private Product(long id, String name, int price, String thumbnailUrl, ProductDetail detail) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.thumbnailUrl = thumbnailUrl;
		this.detail = detail;
	}
	
	
}
