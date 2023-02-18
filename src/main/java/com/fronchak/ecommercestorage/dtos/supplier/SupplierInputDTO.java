package com.fronchak.ecommercestorage.dtos.supplier;

import java.io.Serializable;

public class SupplierInputDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
