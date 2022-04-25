package msa.study.product.controller.dto;

import javax.persistence.Column;

import lombok.Getter;
import msa.study.product.domain.Product;

@Getter
public class ProductDto {
	private long id;
	
	private String name;
	
	private int price;
	
	@Column(name="THUMBNAIL_URL")
	private String thumbnailUrl;
	
	private ProductDto(long id, String name, int price, String thumbnailUrl) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.thumbnailUrl = thumbnailUrl;
	}
	
	public static ProductDto fromProduct(Product product) {
		return new ProductDto(product.getId(), product.getName(), product.getPrice(), product.getThumbnailUrl());
	}
}
