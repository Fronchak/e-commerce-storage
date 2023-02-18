package com.fronchak.ecommercestorage.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fronchak.ecommercestorage.dtos.user.UserInputDTO;
import com.fronchak.ecommercestorage.dtos.user.UserOutputDTO;
import com.fronchak.ecommercestorage.entities.User;

@Service
public class UserMapper {

	public UserOutputDTO convertUserToUserOutputDTO(User entity) {
		return new UserOutputDTO(entity);
	}
	
	public List<UserOutputDTO> convertUserListToUserOutputDTOList(List<User> list) {
		return list.stream()
				.map((entity) -> convertUserToUserOutputDTO(entity))
				.collect(Collectors.toList());
	}
	
	public void copyUserInputDTOToUser(User entity, UserInputDTO dto) {
		entity.setUsername(dto.getUsername());
		entity.setPassword(dto.getPassword());
	}
}
