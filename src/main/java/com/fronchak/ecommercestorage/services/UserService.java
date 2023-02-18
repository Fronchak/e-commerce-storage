package com.fronchak.ecommercestorage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronchak.ecommercestorage.dtos.user.UserInsertDTO;
import com.fronchak.ecommercestorage.dtos.user.UserOutputDTO;
import com.fronchak.ecommercestorage.dtos.user.UserUpdateDTO;
import com.fronchak.ecommercestorage.entities.User;
import com.fronchak.ecommercestorage.exceptions.ResourceNotFoundException;
import com.fronchak.ecommercestorage.mappers.UserMapper;
import com.fronchak.ecommercestorage.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserMapper mapper;
	
	public UserOutputDTO findById(Long id) {
		User entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", id.toString()));
		return mapper.convertUserEntityToUserOutputDTO(entity);
	}
	
	public List<UserOutputDTO> findAll() {
		List<User> entityList = repository.findAll();
		return mapper.convertUserEntityListToUserOutputDTOList(entityList);
	}
	
	@Transactional
	public UserOutputDTO save(UserInsertDTO dto) {
		User entity = new User();
		mapper.copyDTOToEntity(entity, dto);
		entity = repository.save(entity);
		return mapper.convertUserEntityToUserOutputDTO(entity);
	}
	
	@Transactional
	public UserOutputDTO update(UserUpdateDTO dto, Long id) {
		try {
			User entity = repository.getReferenceById(id);
			mapper.copyDTOToEntity(entity, dto);
			entity = repository.save(entity);
			return mapper.convertUserEntityToUserOutputDTO(entity);	
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("User", id.toString());
		}
	}
}
