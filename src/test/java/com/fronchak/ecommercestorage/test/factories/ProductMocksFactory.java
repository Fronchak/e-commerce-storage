package com.fronchak.ecommercestorage.test.factories;

import java.util.ArrayList;
import java.util.List;

import com.fronchak.ecommercestorage.dtos.product.ProductInputDTO;
import com.fronchak.ecommercestorage.dtos.product.ProductInsertDTO;
import com.fronchak.ecommercestorage.dtos.product.ProductOutputDTO;
import com.fronchak.ecommercestorage.dtos.product.ProductUpdateDTO;
import com.fronchak.ecommercestorage.entities.Product;

public class ProductMocksFactory {

	public static Product mockProduct() {
		return mockProduct(0);
	}
	
	public static Product mockProduct(int i) {
		Product mock = new Product();
		mock.setId(mockId(i));
		mock.setName(mockName(i));
		mock.setDescription(mockDescription(i));
		mock.setPrice(mockPrice(i));
		mock.setQuantity(mockQuantity(i));
		return mock;
	}
	
	private static Long mockId(int i) {
		return i + 30L;
	}
	
	private static String mockName(int i) {
		return "Mock product name " + i;
	}
	
	private static String mockDescription(int i) {
		return "Mock product description " + i;
	}
	
	private static Double mockPrice(int i) {
		return 10.0 + i;
	}
	
	private static Integer mockQuantity(int i) {
		return 5 + i;
	}
	
	public static ProductOutputDTO mockProductOutputDTO() {
		return mockProductOutputDTO(0);
	}
	
	public static ProductOutputDTO mockProductOutputDTO(int i) {
		ProductOutputDTO mock = new ProductOutputDTO();
		mock.setId(mockId(i));
		mock.setName(mockName(i));
		mock.setDescription(mockDescription(i));
		mock.setPrice(mockPrice(i));
		mock.setQuantity(mockQuantity(i));
		return mock;
	}
	
	public static List<Product> mockProductList() {
		List<Product> list = new ArrayList<>();
		list.add(mockProduct(0));
		list.add(mockProduct(1));
		return list;
	}
	
	public static List<ProductOutputDTO> mockProductOutputDTOList() {
		List<ProductOutputDTO> list = new ArrayList<>();
		list.add(mockProductOutputDTO(0));
		list.add(mockProductOutputDTO(1));
		return list;
	}
	
	public static ProductInsertDTO mockProductInsertDTO() {
		return (ProductInsertDTO) mockProductInputDTO(new ProductInsertDTO());
	}
	
	private static ProductInputDTO mockProductInputDTO(ProductInputDTO mock) {
		mock.setName(mockName(0));
		mock.setDescription(mockDescription(0));
		mock.setPrice(mockPrice(0));
		return mock;
	}
	
	public static ProductUpdateDTO mockProductUpdateDTO() {
		return (ProductUpdateDTO) mockProductInputDTO(new ProductUpdateDTO());
	}
}
