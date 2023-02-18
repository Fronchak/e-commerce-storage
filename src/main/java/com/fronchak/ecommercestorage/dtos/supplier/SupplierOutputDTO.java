package com.fronchak.ecommercestorage.dtos.supplier;

import java.io.Serializable;

import com.fronchak.ecommercestorage.entities.Supplier;

public class SupplierOutputDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	public SupplierOutputDTO() {}
	
	public SupplierOutputDTO(Supplier supplier) {
		id = supplier.getId();
		name = supplier.getName();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
