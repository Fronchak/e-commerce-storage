package com.fronchak.ecommercestorage.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.fronchak.ecommercestorage.dtos.supplier.SupplierInputDTO;
import com.fronchak.ecommercestorage.dtos.supplier.SupplierInsertDTO;
import com.fronchak.ecommercestorage.dtos.supplier.SupplierOutputDTO;
import com.fronchak.ecommercestorage.dtos.supplier.SupplierUpdateDTO;
import com.fronchak.ecommercestorage.entities.Supplier;
import com.fronchak.ecommercestorage.exceptions.ResourceNotFoundException;
import com.fronchak.ecommercestorage.mappers.SupplierMapper;
import com.fronchak.ecommercestorage.repositories.SupplierRepository;
import com.fronchak.ecommercestorage.test.factories.SupplierMocksFactory;
import com.fronchak.ecommercestorage.test.util.SupplierCustomAsserts;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
public class SupplierServiceTest {

	private Long VALID_ID = 1L;
	private Long INVALID_ID = 2L;
	
	private Supplier entity;
	private List<Supplier> entityList;
	
	private SupplierInsertDTO insertDTO;
	private SupplierUpdateDTO updateDTO;
	
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
		
		insertDTO = SupplierMocksFactory.mockSupplierInsertDTO();
		updateDTO = SupplierMocksFactory.mockSupplierUpdateDTO();
		
		when(repository.findById(VALID_ID)).thenReturn(Optional.of(entity));
		when(repository.findById(INVALID_ID)).thenReturn(Optional.empty());
		when(repository.findAll()).thenReturn(entityList);
		when(repository.save(any(Supplier.class))).thenReturn(entity);
		when(repository.getReferenceById(VALID_ID)).thenReturn(entity);
		when(repository.getReferenceById(INVALID_ID)).thenThrow(EntityNotFoundException.class);
		
		when(mapper.convertSupplierEntityToSupplieroutputDTO(entity)).thenReturn(outputDTO);
		when(mapper.convertSupplierEntityListToSupplierOutputDTOList(entityList)).thenReturn(outputDTOList);
		doNothing().when(mapper).copySupplierDTOToSupplierEntity(any(Supplier.class), any(SupplierInputDTO.class));
	}
	
	@Test
	public void findByIdShouldReturnSupplierOutputDTOWhenIdExists() {
		SupplierOutputDTO result = service.findById(VALID_ID);
		
		SupplierCustomAsserts.assertSupplierOutputDTO(result);
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
		
		SupplierCustomAsserts.assertSupplierOutputDTOList(resultList);
	}
	
	@Test
	public void saveShouldReturnSupplierOutputDTOAfterSaveNewSupplier() {
		ArgumentCaptor<Supplier> argumentCaption = ArgumentCaptor.forClass(Supplier.class);
		SupplierOutputDTO result = service.save(insertDTO);
		
		verify(repository).save(argumentCaption.capture());
		Supplier entitySaved = argumentCaption.getValue();
		assertNull(entitySaved.getId());
		assertNull(entitySaved.getName());
		
		verify(mapper, times(1)).copySupplierDTOToSupplierEntity(entitySaved, insertDTO);
		verify(mapper, times(1)).convertSupplierEntityToSupplieroutputDTO(entity);
		
		SupplierCustomAsserts.assertSupplierOutputDTO(result);
	}
	
	@Test
	public void updateShouldReturnSupplierOutputDTOWhenIdExists() {
		ArgumentCaptor<Supplier> argumentCaption = ArgumentCaptor.forClass(Supplier.class);
		SupplierOutputDTO result = service.update(updateDTO, VALID_ID);
		
		verify(repository).save(argumentCaption.capture());
		Supplier entityUpdated = argumentCaption.getValue();
		assertEquals(0L, entityUpdated.getId());
		assertEquals("Mock supplier name 0", entityUpdated.getName());
		
		verify(repository, times(1)).getReferenceById(VALID_ID);
		verify(mapper, times(1)).copySupplierDTOToSupplierEntity(entityUpdated, updateDTO);
		verify(repository).save(entity);
		verify(mapper, times(1)).convertSupplierEntityToSupplieroutputDTO(entity);
		
		SupplierCustomAsserts.assertSupplierOutputDTO(result);
	}
	
	@Test
	public void updateShouldReturnResourceNotFoundExceptionWhenIdDoesNotExist() {
		assertThrows(ResourceNotFoundException.class, () -> service.update(updateDTO, INVALID_ID));
		
		verify(repository, times(1)).getReferenceById(INVALID_ID);
		verify(repository, never()).save(any());
	}
}
