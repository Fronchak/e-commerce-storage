package com.fronchak.ecommercestorage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fronchak.ecommercestorage.dtos.product.ProductInsertDTO;
import com.fronchak.ecommercestorage.dtos.product.ProductOutputDTO;
import com.fronchak.ecommercestorage.dtos.product.ProductUpdateDTO;
import com.fronchak.ecommercestorage.entities.Product;
import com.fronchak.ecommercestorage.exceptions.ResourceNotFoundException;
import com.fronchak.ecommercestorage.mappers.ProductMapper;
import com.fronchak.ecommercestorage.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired	
	private ProductMapper mapper;
	
	public ProductOutputDTO findById(Long id) {
		Product entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product", id.toString()));
		return mapper.convertProductToProductOutputDTO(entity);
	}
	
	public List<ProductOutputDTO> findAll() {
		List<Product> list = repository.findAll();
		return mapper.convertProductListToProductOutputDTOList(list);
	}
	
	public ProductOutputDTO save(ProductInsertDTO dto) {
		Product entity = new Product();
		mapper.copyProductInputDTOToProduct(entity, dto);
		entity = repository.save(entity);
		return mapper.convertProductToProductOutputDTO(entity);
	}
	
	public ProductOutputDTO update(ProductUpdateDTO dto, Long id) {
		
		return null;
	}
}
