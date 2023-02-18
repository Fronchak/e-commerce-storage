package com.fronchak.ecommercestorage.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fronchak.ecommercestorage.dtos.supplier.SupplierInputDTO;
import com.fronchak.ecommercestorage.dtos.supplier.SupplierOutputDTO;
import com.fronchak.ecommercestorage.entities.Supplier;

@Service
public class SupplierMapper {

	public SupplierOutputDTO convertSupplierEntityToSupplieroutputDTO(Supplier supplier) {
		return new SupplierOutputDTO(supplier);
	}
	
	public List<SupplierOutputDTO> convertSupplierEntityListToSupplierOutputDTOList(List<Supplier> list) {
		return list.stream()
				.map((supplier) -> convertSupplierEntityToSupplieroutputDTO(supplier)) 
				.collect(Collectors.toList());
	}
	
	public void copySupplierDTOToSupplierEntity(Supplier entity, SupplierInputDTO dto) {
		entity.setName(dto.getName());
	}
}
