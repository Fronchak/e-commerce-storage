package com.fronchak.ecommercestorage.test.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.fronchak.ecommercestorage.dtos.delivery_address.DeliveryAddressOutputDTO;
import com.fronchak.ecommercestorage.entities.DeliveryAddress;

public class DeliveryAddressCustomAsserts {

	public static void assertDeliveryAddressOutputDTO(DeliveryAddressOutputDTO result) {
		assertEquals(20L, result.getId());
		assertEquals("Mock delivery address street 0", result.getStreet());
		assertEquals("Mock delivery address number 0", result.getNumber());
		assertEquals("Mock delivery address cep 0", result.getCep());
	}
	
	public static void assertDeliveryAddressOutputList(List<DeliveryAddressOutputDTO> resultList) {
		DeliveryAddressOutputDTO result = resultList.get(0);
		assertEquals(20L, result.getId());
		assertEquals("Mock delivery address street 0", result.getStreet());
		assertEquals("Mock delivery address number 0", result.getNumber());
		assertEquals("Mock delivery address cep 0", result.getCep());
		
		result = resultList.get(1);
		assertEquals(21L, result.getId());
		assertEquals("Mock delivery address street 1", result.getStreet());
		assertEquals("Mock delivery address number 1", result.getNumber());
		assertEquals("Mock delivery address cep 1", result.getCep());
	}
	
	public static void assertDeliveryAddressValues(DeliveryAddress result) {
		assertEquals("Mock delivery address street 0", result.getStreet());
		assertEquals("Mock delivery address number 0", result.getNumber());
		assertEquals("Mock delivery address cep 0", result.getCep());
	}
}
