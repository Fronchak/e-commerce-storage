package com.fronchak.ecommercestorage.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.ecommercestorage.dtos.supplier.SupplierOutputDTO;
import com.fronchak.ecommercestorage.entities.Supplier;
import com.fronchak.ecommercestorage.exceptions.ResourceNotFoundException;
import com.fronchak.ecommercestorage.mappers.SupplierMapper;
import com.fronchak.ecommercestorage.repositories.SupplierRepository;
import com.fronchak.ecommercestorage.test.factories.SupplierMocksFactory;
import com.fronchak.ecommercestorage.test.util.CustomAsserts;

@ExtendWith(SpringExtension.class)
public class SupplierServiceTest {

	private Long VALID_ID = 1L;
	private Long INVALID_ID = 2L;
	private Long DEPENDENT_ID = 3L;
	
	private Supplier entity;
	private List<Supplier> entityList;
	
	@Mock
	private SupplierRepository repository;
	
	@Mock
	private SupplierMapper mapper;
	
	@InjectMocks
	private SupplierService service;
	
	@BeforeEach
	void setUp() {
		entity = SupplierMocksFactory.mockSupplierEntity();
		entityList = SupplierMocksFactory.mockSupplierEntityList();
		SupplierOutputDTO outputDTO = SupplierMocksFactory.mockSupplierOutputDTO();
		List<SupplierOutputDTO> outputDTOList = SupplierMocksFactory.mockSupplierOutputDTOList();
		
		when(repository.findById(VALID_ID)).thenReturn(Optional.of(entity));
		when(repository.findById(INVALID_ID)).thenReturn(Optional.empty());
		when(repository.findAll()).thenReturn(entityList);
		
		when(mapper.convertSupplierEntityToSupplieroutputDTO(entity)).thenReturn(outputDTO);
		when(mapper.convertSupplierEntityListToSupplierOutputDTOList(entityList)).thenReturn(outputDTOList);
	}
	
	@Test
	public void findByIdShouldReturnSupplierOutputDTOWhenIdExists() {
		SupplierOutputDTO result = service.findById(VALID_ID);
		
		CustomAsserts.assertSupplierOutputDTO(result);
		verify(repository, times(1)).findById(VALID_ID);
		verify(mapper, times(1)).convertSupplierEntityToSupplieroutputDTO(entity);
	}
	
	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		assertThrows(ResourceNotFoundException.class, () -> service.findById(INVALID_ID));
	}
	
	@Test
	public void findAllShouldReturnSupplierOutputDTOList() {
		List<SupplierOutputDTO> resultList = service.findAll();
		
		CustomAsserts.assertSupplierOutputDTOList(resultList);
	}
}
