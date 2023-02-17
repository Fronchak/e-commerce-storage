package com.fronchak.ecommercestorage.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "buy")
public class Buy extends Order {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "id_delivery_address")
	private DeliveryAddress address;

	public DeliveryAddress getAddress() {
		return address;
	}

	public void setAddress(DeliveryAddress address) {
		this.address = address;
	}
}
