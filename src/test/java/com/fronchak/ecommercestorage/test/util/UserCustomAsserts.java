package com.fronchak.ecommercestorage.test.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.fronchak.ecommercestorage.dtos.user.UserOutputDTO;

public class UserCustomAsserts {

	public static void assertUserOutputDTO(UserOutputDTO result) {
		assertEquals(10L, result.getId());
		assertEquals("Mock user username 0", result.getUsername());
	}
	
	public static void assertUserOutputDTOList(List<UserOutputDTO> resultList) {
		UserOutputDTO result = resultList.get(0);
		assertEquals(10L, result.getId());
		assertEquals("Mock user username 0", result.getUsername());
		
		result = resultList.get(1);
		assertEquals(11L, result.getId());
		assertEquals("Mock user username 1", result.getUsername());
	}
}
