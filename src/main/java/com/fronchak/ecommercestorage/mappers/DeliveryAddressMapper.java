package com.fronchak.ecommercestorage.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fronchak.ecommercestorage.dtos.delivery_address.DeliveryAddressInputDTO;
import com.fronchak.ecommercestorage.dtos.delivery_address.DeliveryAddressOutputDTO;
import com.fronchak.ecommercestorage.entities.DeliveryAddress;

@Service
public class DeliveryAddressMapper {

	public DeliveryAddressOutputDTO convertDeliveryAddressToDeliveryAddressOutputDTO(DeliveryAddress entity) {
		return new DeliveryAddressOutputDTO(entity);
	}
	
	public List<DeliveryAddressOutputDTO> convertDeliveryAddressListToDeliveryAddressOutputDTOList(List<DeliveryAddress> list) {
		return list.stream()
				.map((entity) -> convertDeliveryAddressToDeliveryAddressOutputDTO(entity))
				.collect(Collectors.toList());
	}
	
	public void copyDeliveryAddressInputDTOToDeliveryAddress(DeliveryAddress entity, DeliveryAddressInputDTO dto) {
		entity.setStreet(dto.getStreet());
		entity.setNumber(dto.getNumber());
		entity.setCep(dto.getCep());
	}
}
