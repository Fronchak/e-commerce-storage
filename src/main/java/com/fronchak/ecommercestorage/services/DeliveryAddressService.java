package com.fronchak.ecommercestorage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronchak.ecommercestorage.dtos.delivery_address.DeliveryAddressInputDTO;
import com.fronchak.ecommercestorage.dtos.delivery_address.DeliveryAddressOutputDTO;
import com.fronchak.ecommercestorage.entities.DeliveryAddress;
import com.fronchak.ecommercestorage.entities.User;
import com.fronchak.ecommercestorage.exceptions.ResourceNotFoundException;
import com.fronchak.ecommercestorage.mappers.DeliveryAddressMapper;
import com.fronchak.ecommercestorage.repositories.DeliveryAddressRepository;
import com.fronchak.ecommercestorage.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DeliveryAddressService {

	@Autowired
	private DeliveryAddressRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DeliveryAddressMapper mapper;
	
	@Transactional
	public DeliveryAddressOutputDTO save(DeliveryAddressInputDTO dto) {
		try {
			DeliveryAddress entity = new DeliveryAddress();
			copyDToToEntity(entity, dto);
			entity = repository.save(entity);
			return mapper.convertDeliveryAddressToDeliveryAddressOutputDTO(entity);	
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("User", dto.getIdUser().toString());
		}
	}
	
	private void copyDToToEntity(DeliveryAddress entity, DeliveryAddressInputDTO dto) {
		User user = userRepository.getReferenceById(dto.getIdUser());
		mapper.copyDeliveryAddressInputDTOToDeliveryAddress(entity, dto);
		entity.setUser(user);
	}
	
	@Transactional
	public DeliveryAddressOutputDTO update(DeliveryAddressInputDTO dto, Long id) {
		
		return null;
	}
	
	public List<DeliveryAddressOutputDTO> findAllByUser(Long idUser) {
		try {
			User user = userRepository.getReferenceById(idUser);
			List<DeliveryAddress> list = repository.findAllByUser(user);
			return mapper.convertDeliveryAddressListToDeliveryAddressOutputDTOList(list);	
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("User", idUser.toString());
		}
	}
}
