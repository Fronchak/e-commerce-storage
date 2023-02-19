package com.fronchak.ecommercestorage.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.ecommercestorage.dtos.product.ProductInsertDTO;
import com.fronchak.ecommercestorage.dtos.product.ProductOutputDTO;
import com.fronchak.ecommercestorage.dtos.product.ProductUpdateDTO;
import com.fronchak.ecommercestorage.entities.Product;
import com.fronchak.ecommercestorage.test.factories.ProductMocksFactory;
import com.fronchak.ecommercestorage.test.util.ProductCustomAssets;

@ExtendWith(SpringExtension.class)
public class ProductMapperTest {

	private ProductMapper mapper;
	
	@BeforeEach
	void setUp() {
		mapper = new ProductMapper();
	}
	
	@Test
	public void convertProductToProductOutputDTOShouldConvertCorrectly() {
		Product entity = ProductMocksFactory.mockProduct();
		
		ProductOutputDTO result = mapper.convertProductToProductOutputDTO(entity);
		
		ProductCustomAssets.assertProductOutputDTO(result);
	}
	
	@Test
	public void convertProductListToProductOutputDTOListShouldConvertCorrectly() {
		List<Product> list = ProductMocksFactory.mockProductList();
		
		List<ProductOutputDTO> resultList = mapper.convertProductListToProductOutputDTOList(list);
		
		ProductCustomAssets.assertProductOutputList(resultList);
	}
	
	@Test
	public void copyProductInputDTOToProductShouldCopyValuesCorrectlyWhenInserting() {
		ProductInsertDTO dto = ProductMocksFactory.mockProductInsertDTO();
		Product entity = new Product();
		entity.setQuantity(0);
		
		mapper.copyProductInputDTOToProduct(entity, dto);
		
		assertNull(entity.getId());
		assertEquals(0, entity.getQuantity());
		assertEquals("Mock product name 0", entity.getName());
		assertEquals("Mock product description 0", entity.getDescription());
		assertEquals(10.0, entity.getPrice());
	}
	
	@Test
	public void copyProductInputDTOToProductShouldCopyValuesCorrectlyWhenUpdating() {
		ProductUpdateDTO dto = ProductMocksFactory.mockProductUpdateDTO();
		Product entity = new Product();
		entity.setQuantity(100);
		entity.setId(1L);
		
		mapper.copyProductInputDTOToProduct(entity, dto);
		
		assertEquals(1L, entity.getId());
		assertEquals(100, entity.getQuantity());
		assertEquals("Mock product name 0", entity.getName());
		assertEquals("Mock product description 0", entity.getDescription());
		assertEquals(10.0, entity.getPrice());
	}
}
