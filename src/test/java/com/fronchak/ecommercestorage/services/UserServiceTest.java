package com.fronchak.ecommercestorage.services;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.ecommercestorage.dtos.user.UserInputDTO;
import com.fronchak.ecommercestorage.dtos.user.UserInsertDTO;
import com.fronchak.ecommercestorage.dtos.user.UserOutputDTO;
import com.fronchak.ecommercestorage.dtos.user.UserUpdateDTO;
import com.fronchak.ecommercestorage.entities.User;
import com.fronchak.ecommercestorage.exceptions.ResourceNotFoundException;
import com.fronchak.ecommercestorage.mappers.UserMapper;
import com.fronchak.ecommercestorage.repositories.UserRepository;
import com.fronchak.ecommercestorage.test.factories.UserMocksFactory;
import com.fronchak.ecommercestorage.test.util.UserCustomAsserts;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

	private Long VALID_ID = 1L;
	private Long INVALID_ID = 2L;
	
	private User entity;
	private List<User> entityList;
	private UserInsertDTO insertDTO;
	private UserUpdateDTO updateDTO;
	
	@Mock
	private UserRepository repository;
	
	@Mock
	private UserMapper mapper;
	
	@InjectMocks
	private UserService service;
	
	@BeforeEach
	void setUp() {
		entity = UserMocksFactory.mockUser();
		entityList = UserMocksFactory.mockUserList();
		
		UserOutputDTO outputDTO = UserMocksFactory.mockUserOutputDTO();
		List<UserOutputDTO> outputDTOList = UserMocksFactory.mockUserOutputDTOList();
		
		when(repository.findById(VALID_ID)).thenReturn(Optional.of(entity));
		when(repository.findById(INVALID_ID)).thenReturn(Optional.empty());
		when(repository.findAll()).thenReturn(entityList);
		when(repository.save(any(User.class))).thenReturn(entity);
		when(repository.getReferenceById(VALID_ID)).thenReturn(entity);
		when(repository.getReferenceById(INVALID_ID)).thenThrow(EntityNotFoundException.class);
		
		when(mapper.convertUserToUserOutputDTO(entity)).thenReturn(outputDTO);
		when(mapper.convertUserListToUserOutputDTOList(entityList)).thenReturn(outputDTOList);
		doNothing().when(mapper).copyUserInputDTOToUser(any(User.class), any(UserInputDTO.class));
		
	}
	
	@Test
	public void findByIdShouldReturnUserOutputDTOWhenIdExists() {
		UserOutputDTO result = service.findById(VALID_ID);
		
		verify(repository, times(1)).findById(VALID_ID);
		verify(mapper, times(1)).convertUserToUserOutputDTO(entity);
		UserCustomAsserts.assertUserOutputDTO(result);
	}
	
	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		assertThrows(ResourceNotFoundException.class, () -> service.findById(INVALID_ID));
	}
	
	@Test
	public void findAllShouldReturnUserOutputDTOList() {
		List<UserOutputDTO> resultList = service.findAll();
		
		verify(repository, times(1)).findAll();
		verify(mapper, times(1)).convertUserListToUserOutputDTOList(entityList);
		UserCustomAsserts.assertUserOutputDTOList(resultList);
	}
	
	@Test
	public void saveShouldReturnUserOutputDTOAfterSaveEntity() {
		ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
		UserOutputDTO result = service.save(insertDTO);
		
		verify(repository).save(argumentCaptor.capture());
		User userSaved = argumentCaptor.getValue();
		assertNull(userSaved.getId());
		assertNull(userSaved.getUsername());
		assertNull(userSaved.getPassword());
		
		verify(mapper, times(1)).copyUserInputDTOToUser(userSaved, insertDTO);
		verify(repository).save(any(User.class));
		
		verify(mapper, times(1)).convertUserToUserOutputDTO(entity);
		UserCustomAsserts.assertUserOutputDTO(result);
	}
	
	@Test
	public void updateShouldReturnUserOutputDTOWhenIdExists() {
		ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
		UserOutputDTO result = service.update(updateDTO, VALID_ID);
		
		verify(repository).save(argumentCaptor.capture());
		User entityUpdated = argumentCaptor.getValue();
		UserCustomAsserts.assertUserEntity(entityUpdated);
		
		verify(repository, times(1)).getReferenceById(VALID_ID);
		verify(mapper, times(1)).copyUserInputDTOToUser(entityUpdated, insertDTO);
		verify(repository, times(1)).save(entity);

		verify(mapper, times(1)).convertUserToUserOutputDTO(entity);
		UserCustomAsserts.assertUserOutputDTO(result);
	}
	
	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		assertThrows(ResourceNotFoundException.class, () -> service.update(updateDTO, INVALID_ID));
		
		verify(repository, times(1)).getReferenceById(INVALID_ID);
		verify(repository, never()).save(any());
	}
}
