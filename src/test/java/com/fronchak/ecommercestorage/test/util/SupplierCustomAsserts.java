package com.fronchak.ecommercestorage.test.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.fronchak.ecommercestorage.dtos.supplier.SupplierOutputDTO;

public class SupplierCustomAsserts {

	public static void assertSupplierOutputDTO(SupplierOutputDTO result) {
		assertEquals(0L, result.getId());
		assertEquals("Mock supplier name 0", result.getName());
	}
	
	public static void assertSupplierOutputDTOList(List<SupplierOutputDTO> resultList) {
		SupplierOutputDTO result = resultList.get(0);
		assertEquals(0L, result.getId());
		assertEquals("Mock supplier name 0", result.getName());
		
		result = resultList.get(1);
		assertEquals(1L, result.getId());
		assertEquals("Mock supplier name 1", result.getName());
	}
}
