package msa.study.product.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import msa.study.product.domain.common.BaseEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="PRODUCT")
public class Product extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PRODUCT_NO")
	private long id;
	
	private String name;
	
	private int price;
	
	@Column(name="THUMBNAIL_URL")
	private String thumbnailUrl;

	@Builder
	private Product(long id, String name, int price, String thumbnailUrl) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.thumbnailUrl = thumbnailUrl;
	}
}
