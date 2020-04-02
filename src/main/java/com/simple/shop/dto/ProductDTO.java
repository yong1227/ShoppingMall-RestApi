package com.simple.shop.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ProductDTO {

	private Long id;
	private String name;
	private String content;
	private String price;
	private String salePrice;
	private String image;
	private Date createdAt;
	private Boolean isBasket;
	
}
