package com.fronchak.ecommercestorage.dtos.product;

import java.io.Serializable;

public class ProductInputDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String description;
	private Double price;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
}
