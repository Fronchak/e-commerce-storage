package com.fronchak.ecommercestorage.dtos.supplier;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.ecommercestorage.dtos.user.UserOutputDTO;
import com.fronchak.ecommercestorage.entities.User;
import com.fronchak.ecommercestorage.test.factories.UserMocksFactory;
import com.fronchak.ecommercestorage.test.util.UserCustomAsserts;

@ExtendWith(SpringExtension.class)
public class UserOutputDTOTest {

	@Test
	public void constructorWithUserEntityShouldCreateCorrectlyUserOutputDTO() {
		User entity = UserMocksFactory.mockEntityUser();
		UserOutputDTO result = new UserOutputDTO(entity);
		UserCustomAsserts.assertUserOutputDTO(result);
	}
}
