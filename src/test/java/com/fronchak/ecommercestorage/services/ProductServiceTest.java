package com.fronchak.ecommercestorage.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
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

import com.fronchak.ecommercestorage.dtos.product.ProductInputDTO;
import com.fronchak.ecommercestorage.dtos.product.ProductInsertDTO;
import com.fronchak.ecommercestorage.dtos.product.ProductOutputDTO;
import com.fronchak.ecommercestorage.dtos.product.ProductUpdateDTO;
import com.fronchak.ecommercestorage.entities.Product;
import com.fronchak.ecommercestorage.exceptions.ResourceNotFoundException;
import com.fronchak.ecommercestorage.mappers.ProductMapper;
import com.fronchak.ecommercestorage.repositories.ProductRepository;
import com.fronchak.ecommercestorage.test.factories.ProductMocksFactory;
import com.fronchak.ecommercestorage.test.util.ProductCustomAssets;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

	private final Long VALID_ID = 1L;
	private final Long INVALID_ID = 2L;
	
	private Product entity;
	private List<Product> entityList;
	private ProductInsertDTO insertDTO;
	private ProductUpdateDTO updateDTO;
	
	@Mock
	private ProductRepository repository;
	
	@Mock
	private ProductMapper mapper;
	
	@InjectMocks
	private ProductService service;
	
	@BeforeEach
	void setUp() {
		entity = ProductMocksFactory.mockProduct();
		entityList = ProductMocksFactory.mockProductList();
		
		ProductOutputDTO outputDTO = ProductMocksFactory.mockProductOutputDTO();
		List<ProductOutputDTO> outputDTOList = ProductMocksFactory.mockProductOutputDTOList();
		
		when(repository.findById(VALID_ID)).thenReturn(Optional.of(entity));
		when(repository.findById(INVALID_ID)).thenReturn(Optional.empty());
		when(repository.findAll()).thenReturn(entityList);
		when(repository.save(any(Product.class))).thenReturn(entity);
		
		when(mapper.convertProductToProductOutputDTO(entity)).thenReturn(outputDTO);
		when(mapper.convertProductListToProductOutputDTOList(entityList)).thenReturn(outputDTOList);
		doNothing().when(mapper).copyProductInputDTOToProduct(any(Product.class), any(ProductInputDTO.class));
	}
	
	@Test
	public void findByIdShouldReturnProductWhenIdExists() {
		ProductOutputDTO result = service.findById(VALID_ID);
		
		verify(repository, times(1)).findById(VALID_ID);
		verify(mapper, times(1)).convertProductToProductOutputDTO(entity);
		ProductCustomAssets.assertProductOutputDTO(result);
	}
	
	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		assertThrows(ResourceNotFoundException.class, () -> service.findById(INVALID_ID));
	}
	
	@Test
	public void findAllShouldReturnProductOutputDTOList() {
		List<ProductOutputDTO> resultList = service.findAll();
		
		verify(repository, times(1)).findAll();
		verify(mapper, times(1)).convertProductListToProductOutputDTOList(entityList);
		ProductCustomAssets.assertProductOutputList(resultList);
	}
	
	@Test
	public void saveShouldReturnOutputDTOAfterSaveNewProductWithZeroQuantity() {
		ArgumentCaptor<Product> argumentCaptor = ArgumentCaptor.forClass(Product.class);
		ProductOutputDTO result = service.save(insertDTO);

		verify(repository, times(1)).save(argumentCaptor.capture());
		Product entitySaved = argumentCaptor.getValue();
		assertNull(entitySaved.getId());
		assertNull(entitySaved.getName());
		assertNull(entitySaved.getDescription());
		assertNull(entitySaved.getPrice());
		assertEquals(0, entitySaved.getQuantity());
		
		verify(mapper, times(1)).copyProductInputDTOToProduct(entitySaved, insertDTO);
		verify(mapper, times(1)).convertProductToProductOutputDTO(entity);
		ProductCustomAssets.assertProductOutputDTO(result);
	}
}
