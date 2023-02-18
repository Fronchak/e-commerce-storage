package com.fronchak.ecommercestorage.dtos.delivery_address;

import java.io.Serializable;

public class DeliveryAddressInputDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String street;
	private String number;
	private String cep;
	private Long idUser;
	
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
	
	public Long getIdUser() {
		return idUser;
	}
	
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
}
