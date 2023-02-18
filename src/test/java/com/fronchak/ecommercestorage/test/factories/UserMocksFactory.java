package com.fronchak.ecommercestorage.test.factories;

import java.util.ArrayList;
import java.util.List;

import com.fronchak.ecommercestorage.dtos.user.UserInputDTO;
import com.fronchak.ecommercestorage.dtos.user.UserInsertDTO;
import com.fronchak.ecommercestorage.dtos.user.UserOutputDTO;
import com.fronchak.ecommercestorage.dtos.user.UserUpdateDTO;
import com.fronchak.ecommercestorage.entities.User;

public class UserMocksFactory {

	public static User mockEntityUser() {
		return mockEntityUser(0);
	}
	
	public static User mockEntityUser(int i) {
		User mock = new User();
		mock.setId(mockUserId(i));
		mock.setUsername(mockUserUsername(i));
		mock.setPassword(mockUserPassword(i));
		return mock;
	}
	
	private static Long mockUserId(int i) {
		return i + 10L;
	}
	
	private static String mockUserUsername(int i) {
		return "Mock user username " + i;
	}
	
	private static String mockUserPassword(int i) {
		return "Mock user password " + i;
 	}
	
	public static UserOutputDTO mockUserOutputDTO() {
		return mockUserOutputDTO(0);
	}
	
	public static UserOutputDTO mockUserOutputDTO(int i) {
		UserOutputDTO mock = new UserOutputDTO();
		mock.setUsername(mockUserUsername(i));
		return mock;
	}
	
	public static List<User> mockUserEntityList() {
		List<User> list = new ArrayList<>();
		list.add(mockEntityUser(0));
		list.add(mockEntityUser(1));
		return list;
	}
	
	public static List<UserOutputDTO> mockUserOutputDTOList() {
		List<UserOutputDTO> list = new ArrayList<>();
		list.add(mockUserOutputDTO(0));
		list.add(mockUserOutputDTO(1));
		return list;
	}
	
	public static UserInsertDTO mockUserInsertDTO() {
		return (UserInsertDTO) mockUserInputDTO(new UserInsertDTO());
	}
	
	private static UserInputDTO mockUserInputDTO(UserInputDTO mock) {
		mock.setUsername(mockUserUsername(0));
		mock.setPassword(mockUserPassword(0));
		return mock;
	}
	
	public static UserUpdateDTO mockUserUpdateDTO() {
		return (UserUpdateDTO) mockUserInputDTO(new UserUpdateDTO());
	}
}
