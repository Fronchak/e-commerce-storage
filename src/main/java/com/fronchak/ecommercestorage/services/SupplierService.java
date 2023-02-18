package com.fronchak.ecommercestorage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fronchak.ecommercestorage.dtos.supplier.SupplierOutputDTO;
import com.fronchak.ecommercestorage.entities.Supplier;
import com.fronchak.ecommercestorage.mappers.SupplierMapper;
import com.fronchak.ecommercestorage.repositories.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository repository;
	
	@Autowired
	private SupplierMapper mapper;
	
	public SupplierOutputDTO findById(Long id) {
		Supplier entity = repository.findById(id).get();
		return mapper.convertSupplierEntityToSupplieroutputDTO(entity);
	}
	
	public List<SupplierOutputDTO> findAll() {
		List<Supplier> entityList = repository.findAll();
		return mapper.convertSupplierEntityListToSupplierOutputDTOList(entityList);
	}
}
