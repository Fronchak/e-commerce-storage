package com.fronchak.ecommercestorage.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fronchak.ecommercestorage.dtos.product.ProductInputDTO;
import com.fronchak.ecommercestorage.dtos.product.ProductOutputDTO;
import com.fronchak.ecommercestorage.entities.Product;

@Service
public class ProductMapper {

	public ProductOutputDTO convertProductToProductOutputDTO(Product entity) {
		return new ProductOutputDTO(entity);
	}
	
	public List<ProductOutputDTO> convertProductListToProductOutputDTOList(List<Product> list) {
		return list.stream()
				.map((entity) -> convertProductToProductOutputDTO(entity))
				.collect(Collectors.toList());
	}
	
	public void copyProductInputDTOToProduct(Product entity, ProductInputDTO dto) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
	}
}
