package com.fronchak.ecommercestorage.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.ecommercestorage.dtos.delivery_address.DeliveryAddressInputDTO;
import com.fronchak.ecommercestorage.dtos.delivery_address.DeliveryAddressOutputDTO;
import com.fronchak.ecommercestorage.entities.DeliveryAddress;
import com.fronchak.ecommercestorage.entities.User;
import com.fronchak.ecommercestorage.exceptions.ResourceNotFoundException;
import com.fronchak.ecommercestorage.mappers.DeliveryAddressMapper;
import com.fronchak.ecommercestorage.repositories.DeliveryAddressRepository;
import com.fronchak.ecommercestorage.repositories.UserRepository;
import com.fronchak.ecommercestorage.test.factories.DeliveryAddressMocksFactory;
import com.fronchak.ecommercestorage.test.factories.UserMocksFactory;
import com.fronchak.ecommercestorage.test.util.DeliveryAddressCustomAsserts;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
public class DeliveryAddressServiceTest {

	private Long VALID_USER_ID = 10L;
	private Long INVALID_USER_ID = 11L;
	
	private DeliveryAddress entity;
	private List<DeliveryAddress> entityList;
	private DeliveryAddressInputDTO inputDTO;
	
	private User user;
	
	@Mock
	private DeliveryAddressRepository repository;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private DeliveryAddressMapper mapper;
	
	@InjectMocks
	private DeliveryAddressService service;
	
	@BeforeEach
	void setUp() {
		entity = DeliveryAddressMocksFactory.mockDeliveryAddress();
		entityList = DeliveryAddressMocksFactory.mockDeliveryAddressList();
		
		DeliveryAddressOutputDTO outputDTO = DeliveryAddressMocksFactory.mockDeliveryAddressOutputDTO();
		List<DeliveryAddressOutputDTO> outputDTOList = DeliveryAddressMocksFactory.mockDeliveryAddressOutputDTOList();
		inputDTO = DeliveryAddressMocksFactory.mockDeliveryAddressInputDTO();
		
		user = UserMocksFactory.mockUser();
		
		when(repository.findAllByUser(user)).thenReturn(entityList);
		when(repository.save(any(DeliveryAddress.class))).thenReturn(entity);
		
		when(userRepository.getReferenceById(VALID_USER_ID)).thenReturn(user);
		when(userRepository.getReferenceById(INVALID_USER_ID)).thenThrow(EntityNotFoundException.class);
		
		when(mapper.convertDeliveryAddressToDeliveryAddressOutputDTO(entity)).thenReturn(outputDTO);
		when(mapper.convertDeliveryAddressListToDeliveryAddressOutputDTOList(entityList))
			.thenReturn(outputDTOList);
		doNothing().when(mapper).copyDeliveryAddressInputDTOToDeliveryAddress(
				any(DeliveryAddress.class), eq(inputDTO));
		
	}
	
	@Test
	public void findAllByUserShouldReturnDeliveryOutputDTOListWhenUserExists() {
		List<DeliveryAddressOutputDTO> resultList = service.findAllByUser(VALID_USER_ID);
		
		verify(userRepository, times(1)).getReferenceById(VALID_USER_ID);
		verify(repository, times(1)).findAllByUser(user);
		verify(mapper, times(1)).convertDeliveryAddressListToDeliveryAddressOutputDTOList(entityList);
		DeliveryAddressCustomAsserts.assertDeliveryAddressOutputList(resultList);
	}
	
	@Test
	public void findAllByUserShouldThrowResourceNotFoundExceptionWhenUserDoesNotExist() {
		assertThrows(ResourceNotFoundException.class, () -> service.findAllByUser(INVALID_USER_ID));
		
		verify(userRepository, times(1)).getReferenceById(INVALID_USER_ID);
	}
	
	@Test
	public void saveShouldReturnDeliveryAddressAfterSaveEntityWhenUserIdExists() {
		ArgumentCaptor<DeliveryAddress> argumentCaptor = ArgumentCaptor.forClass(DeliveryAddress.class);
		Long idUser = inputDTO.getIdUser();

		when(userRepository.getReferenceById(idUser)).thenReturn(user);
		
		DeliveryAddressOutputDTO result = service.save(inputDTO);
		
		verify(repository).save(argumentCaptor.capture());
		DeliveryAddress entitySaved = argumentCaptor.getValue();
		assertNull(entitySaved.getId());
		assertNull(entitySaved.getStreet());
		assertNull(entitySaved.getNumber());
		assertNull(entitySaved.getCep());
		assertEquals(user, entitySaved.getUser());
		
		verify(userRepository, times(1)).getReferenceById(idUser);
		verify(mapper, times(1)).copyDeliveryAddressInputDTOToDeliveryAddress(entitySaved, inputDTO);
		verify(mapper, times(1)).convertDeliveryAddressToDeliveryAddressOutputDTO(entity);
		DeliveryAddressCustomAsserts.assertDeliveryAddressOutputDTO(result);
	}
	
	@Test
	public void saveShouldThrowResourceNotFoundExceptionWhenUserIdDoesNotExist() {
		Long idUSer = inputDTO.getIdUser();
		
		when(userRepository.getReferenceById(idUSer)).thenThrow(EntityNotFoundException.class);
		
		assertThrows(ResourceNotFoundException.class, () -> service.save(inputDTO));
		verify(userRepository, times(1)).getReferenceById(idUSer);
		verify(repository, never()).save(any());
	}
}
