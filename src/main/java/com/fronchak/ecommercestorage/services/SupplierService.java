package com.fronchak.ecommercestorage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronchak.ecommercestorage.dtos.supplier.SupplierInsertDTO;
import com.fronchak.ecommercestorage.dtos.supplier.SupplierOutputDTO;
import com.fronchak.ecommercestorage.dtos.supplier.SupplierUpdateDTO;
import com.fronchak.ecommercestorage.entities.Supplier;
import com.fronchak.ecommercestorage.exceptions.ResourceNotFoundException;
import com.fronchak.ecommercestorage.mappers.SupplierMapper;
import com.fronchak.ecommercestorage.repositories.SupplierRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository repository;
	
	@Autowired
	private SupplierMapper mapper;
	
	public SupplierOutputDTO findById(Long id) {
		Supplier entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Supplier", id.toString()));
		return mapper.convertSupplierEntityToSupplieroutputDTO(entity);
	}
	
	public List<SupplierOutputDTO> findAll() {
		List<Supplier> entityList = repository.findAll();
		return mapper.convertSupplierEntityListToSupplierOutputDTOList(entityList);
	}
	
	@Transactional
	public SupplierOutputDTO save(SupplierInsertDTO dto) {
		Supplier entity = new Supplier();
		mapper.copySupplierDTOToSupplierEntity(entity, dto);
		entity = repository.save(entity);
		return mapper.convertSupplierEntityToSupplieroutputDTO(entity);
	}
	
	@Transactional
	public SupplierOutputDTO update(SupplierUpdateDTO dto, Long id) {
		try {
			Supplier entity = repository.getReferenceById(id);
			mapper.copySupplierDTOToSupplierEntity(entity, dto);
			entity = repository.save(entity);
			return mapper.convertSupplierEntityToSupplieroutputDTO(entity);	
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Supplier", id.toString());
		}
	}
}
