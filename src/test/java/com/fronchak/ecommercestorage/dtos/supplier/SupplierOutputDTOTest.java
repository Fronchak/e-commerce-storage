package com.fronchak.ecommercestorage.dtos.supplier;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.ecommercestorage.entities.Supplier;
import com.fronchak.ecommercestorage.test.factories.SupplierMocksFactory;
import com.fronchak.ecommercestorage.test.util.SupplierCustomAsserts;

@ExtendWith(SpringExtension.class)
public class SupplierOutputDTOTest {

	@Test
	public void constructorWithSupplierEntityShouldCreateCorrectlyOutputDTO() {
		Supplier entity = SupplierMocksFactory.mockSupplierEntity();
		SupplierOutputDTO result = new SupplierOutputDTO(entity);
		SupplierCustomAsserts.assertSupplierOutputDTO(result);
	}
}
