package com.fronchak.ecommercestorage.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.ecommercestorage.dtos.supplier.SupplierInsertDTO;
import com.fronchak.ecommercestorage.dtos.supplier.SupplierOutputDTO;
import com.fronchak.ecommercestorage.dtos.supplier.SupplierUpdateDTO;
import com.fronchak.ecommercestorage.entities.Supplier;
import com.fronchak.ecommercestorage.test.factories.SupplierMocksFactory;
import com.fronchak.ecommercestorage.test.util.CustomAsserts;

@ExtendWith(SpringExtension.class)
public class SupplierMapperTest {

	private SupplierMapper mapper;
	
	@BeforeEach
	void setUp() {
		mapper = new SupplierMapper();
	}
	
	@Test
	public void convertSupplierEntityToSupplierOutoutDTOShouldConvertCorrectly() {
		Supplier entity = SupplierMocksFactory.mockSupplierEntity();
		
		SupplierOutputDTO result = mapper.convertSupplierEntityToSupplieroutputDTO(entity);
		
		CustomAsserts.assertSupplierOutputDTO(result);
	}
	
	@Test
	public void convertSupplierEntityListToSupplierOutputDTOShouldConvertCorrectly() {
		List<Supplier> entityList = SupplierMocksFactory.mockSupplierEntityList();
		
		List<SupplierOutputDTO> resultList = mapper.convertSupplierEntityListToSupplierOutputDTOList(entityList);
		
		CustomAsserts.assertSupplierOutputDTOList(resultList);
	}
	
	@Test 
	public void copyInputDTOToEntityShouldCopyTheValuesCorrectlyWhenUsingAUpdateDTO() {
		SupplierInsertDTO dto = SupplierMocksFactory.mockSupplierInsertDTO();
		Supplier entity = new Supplier();
		
		mapper.copySupplierDTOToSupplierEntity(entity, dto);
		
		assertEquals("Mock supplier name 0", entity.getName());
		assertNull(entity.getId());
	}
	
	@Test 
	public void copyInputDTOToEntityShouldCopyTheValuesCorrectlyWhenUsingAInsertDTO() {
		SupplierUpdateDTO dto = SupplierMocksFactory.mockSupplierUpdateDTO();
		Supplier entity = new Supplier();
		entity.setId(1L);
		
		mapper.copySupplierDTOToSupplierEntity(entity, dto);
		
		assertEquals("Mock supplier name 0", entity.getName());
		assertEquals(1L, entity.getId());
	}
}
