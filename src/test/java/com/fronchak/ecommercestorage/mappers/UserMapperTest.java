package com.fronchak.ecommercestorage.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.ecommercestorage.dtos.user.UserInsertDTO;
import com.fronchak.ecommercestorage.dtos.user.UserOutputDTO;
import com.fronchak.ecommercestorage.dtos.user.UserUpdateDTO;
import com.fronchak.ecommercestorage.entities.User;
import com.fronchak.ecommercestorage.test.factories.UserMocksFactory;
import com.fronchak.ecommercestorage.test.util.UserCustomAsserts;

@ExtendWith(SpringExtension.class)
public class UserMapperTest {

	private UserMapper mapper;
	
	@BeforeEach
	void setUp() {
		mapper = new UserMapper();
	}
	
	@Test
	public void convertUserEntityToUserOutputDTOShouldConvertCorrectly() {
		User entity = UserMocksFactory.mockUser();
		
		UserOutputDTO result = mapper.convertUserToUserOutputDTO(entity);
		
		UserCustomAsserts.assertUserOutputDTO(result);
	}
	
	@Test
	public void convertUserEntityListToUserOutputDTOListShouldConvertCorrectly() {
		List<User> list = UserMocksFactory.mockUserList();
		
		List<UserOutputDTO> resultList = mapper.convertUserListToUserOutputDTOList(list);
		
		UserCustomAsserts.assertUserOutputDTOList(resultList);
	}
	
	@Test
	public void copyUserInputDTOToUserEntityShouldCopyValuesCorrectlyWhenUsingInsertDTO() {
		UserInsertDTO dto = UserMocksFactory.mockUserInsertDTO();
		User entity = new User();
		
		mapper.copyUserInputDTOToUser(entity, dto);
		
		assertEquals("Mock user username 0", entity.getUsername());
		assertEquals("Mock user password 0", entity.getPassword());
		assertNull(entity.getId());
	}
	
	@Test
	public void copyUserInputDTOToUserEntityShouldCopyValuesCorrectlyWhenUsingUPdateDTO() {
		UserUpdateDTO dto = UserMocksFactory.mockUserUpdateDTO();
		User entity = new User();
		entity.setId(1L);
	
		mapper.copyUserInputDTOToUser(entity, dto);
		
		assertEquals("Mock user username 0", entity.getUsername());
		assertEquals("Mock user password 0", entity.getPassword());
		assertEquals(1L, entity.getId());
	}
	
	
}
