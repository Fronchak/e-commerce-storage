package com.fronchak.ecommercestorage.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.ecommercestorage.entities.Product;
import com.fronchak.ecommercestorage.exceptions.NotEnoughInStockException;

@ExtendWith(SpringExtension.class)
public class ProductTest {

	private Product product;
	
	@BeforeEach
	void setUp() {
		product = new Product();
	}
	
	@Test
	public void sellShouldSubtratProductQuantityWhenThereIsEnoughInStock() {
		product.setQuantity(10);
		
		product.sell(4);
		
		assertEquals(6, product.getQuantity());
	}
	
	@Test
	public void sellShouldThrowNotEnoughInStockExceptionWhenQuantityToSellInGreaterThanQuantityInStock() {
		product.setQuantity(10);
		
		assertThrows(NotEnoughInStockException.class, () -> product.sell(11));
		assertEquals(10, product.getQuantity());
	}
	
	@Test
	public void buyShouldIncreaseQuantityInStock() {
		product.setQuantity(10);
		
		product.buy(5);
		
		assertEquals(15, product.getQuantity());
	}
	
	@Test
	public void productShouldBeCreatedWithQuantityEqualsToZero() {
		assertEquals(0, product.getQuantity());
	}
}
