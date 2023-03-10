package com.fronchak.ecommercestorage.test.factories;

import java.util.ArrayList;
import java.util.List;

import com.fronchak.ecommercestorage.dtos.user.UserInputDTO;
import com.fronchak.ecommercestorage.dtos.user.UserInsertDTO;
import com.fronchak.ecommercestorage.dtos.user.UserOutputDTO;
import com.fronchak.ecommercestorage.dtos.user.UserUpdateDTO;
import com.fronchak.ecommercestorage.entities.User;

public class UserMocksFactory {

	public static User mockUser() {
		return mockUser(0);
	}
	
	public static User mockUser(int i) {
		User mock = new User();
		mock.setId(mockId(i));
		mock.setUsername(mockUsername(i));
		mock.setPassword(mockPassword(i));
		return mock;
	}
	
	private static Long mockId(int i) {
		return i + 10L;
	}
	
	private static String mockUsername(int i) {
		return "Mock user username " + i;
	}
	
	private static String mockPassword(int i) {
		return "Mock user password " + i;
 	}
	
	public static UserOutputDTO mockUserOutputDTO() {
		return mockUserOutputDTO(0);
	}
	
	public static UserOutputDTO mockUserOutputDTO(int i) {
		UserOutputDTO mock = new UserOutputDTO();
		mock.setId(mockId(i));
		mock.setUsername(mockUsername(i));
		return mock;
	}
	
	public static List<User> mockUserList() {
		List<User> list = new ArrayList<>();
		list.add(mockUser(0));
		list.add(mockUser(1));
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
		mock.setUsername(mockUsername(0));
		mock.setPassword(mockPassword(0));
		return mock;
	}
	
	public static UserUpdateDTO mockUserUpdateDTO() {
		return (UserUpdateDTO) mockUserInputDTO(new UserUpdateDTO());
	}
}
