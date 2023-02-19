package com.fronchak.ecommercestorage.dtos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.ecommercestorage.dtos.product.ProductOutputDTO;
import com.fronchak.ecommercestorage.entities.Product;
import com.fronchak.ecommercestorage.test.factories.ProductMocksFactory;
import com.fronchak.ecommercestorage.test.util.ProductCustomAssets;

@ExtendWith(SpringExtension.class)
public class ProductOutputDTOTest {

	@Test
	public void constructorWithProductShouldCreateCorrectlyProductOutputDTO() {
		Product entity = ProductMocksFactory.mockProduct();
		
		ProductOutputDTO result = new ProductOutputDTO(entity);
		
		ProductCustomAssets.assertProductOutputDTO(result);
	}
}
