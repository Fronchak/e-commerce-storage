package com.fronchak.ecommercestorage.dtos.delivery_address;

import java.io.Serializable;

import com.fronchak.ecommercestorage.entities.DeliveryAddress;

public class DeliveryAddressOutputDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String street;
	private String number;
	private String cep;
	
	public DeliveryAddressOutputDTO() {};
	
	public DeliveryAddressOutputDTO(DeliveryAddress entity) {
		id = entity.getId();
		street = entity.getStreet();
		number = entity.getNumber();
		cep = entity.getCep();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
}
