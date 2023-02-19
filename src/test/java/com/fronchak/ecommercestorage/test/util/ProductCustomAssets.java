package com.fronchak.ecommercestorage.test.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.fronchak.ecommercestorage.dtos.product.ProductOutputDTO;

public class ProductCustomAssets {

	public static void assertProductOutputDTO(ProductOutputDTO result) {
		assertEquals(30L, result.getId());
		assertEquals("Mock product name 0", result.getName());
		assertEquals("Mock product description 0", result.getDescription());
		assertEquals(10.0, result.getPrice());
		assertEquals(5, result.getQuantity());
	}
	
	public static void assertProductOutputList(List<ProductOutputDTO> resultList) {
		ProductOutputDTO result = resultList.get(0);
		assertEquals(30L, result.getId());
		assertEquals("Mock product name 0", result.getName());
		assertEquals("Mock product description 0", result.getDescription());
		assertEquals(10.0, result.getPrice());
		assertEquals(5, result.getQuantity());
		
		result = resultList.get(1);
		assertEquals(31L, result.getId());
		assertEquals("Mock product name 1", result.getName());
		assertEquals("Mock product description 1", result.getDescription());
		assertEquals(11.0, result.getPrice());
		assertEquals(6, result.getQuantity());
	}
}
