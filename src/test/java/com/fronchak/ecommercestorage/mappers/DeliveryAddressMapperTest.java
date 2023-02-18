package com.fronchak.ecommercestorage.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.ecommercestorage.dtos.delivery_address.DeliveryAddressInputDTO;
import com.fronchak.ecommercestorage.dtos.delivery_address.DeliveryAddressOutputDTO;
import com.fronchak.ecommercestorage.entities.DeliveryAddress;
import com.fronchak.ecommercestorage.test.factories.DeliveryAddressMockFactory;
import com.fronchak.ecommercestorage.test.util.DeliveryAddressCustomAsserts;

@ExtendWith(SpringExtension.class)
public class DeliveryAddressMapperTest {

	private DeliveryAddressMapper mapper;
	
	@BeforeEach
	void setUp() {
		mapper = new DeliveryAddressMapper();
	}
	
	@Test
	public void convertDeliveryAddressToDeliveryAddressOutputDTOShouldConvertCorrectly() {
		DeliveryAddress entity = DeliveryAddressMockFactory.mockDeliveryAddress();
		
		DeliveryAddressOutputDTO result = mapper.convertDeliveryAddressToDeliveryAddressOutputDTO(entity);
		
		DeliveryAddressCustomAsserts.assertDeliveryAddressOutputDTO(result);
	}
	
	@Test
	public void convertDeliveryAddressListToDeliveryAddressOutputDTOListShouldConvertCorrectly() {
		List<DeliveryAddress> list = DeliveryAddressMockFactory.mockDeliveryAddressList();
		
		List<DeliveryAddressOutputDTO> resultList = mapper.convertDeliveryAddressListToDeliveryAddressOutputDTOList(list);
		
		DeliveryAddressCustomAsserts.assertDeliveryAddressOutputList(resultList);
	}
	
	@Test
	public void copyDeliveryAddressInputDTOToDeliveryAddressShouldCopyValuesCorrectlyWhenInserting() {
		DeliveryAddressInputDTO dto = DeliveryAddressMockFactory.mockDeliveryAddressInputDTO();
		DeliveryAddress entity = new DeliveryAddress();
		
		mapper.copyDeliveryAddressInputDTOToDeliveryAddress(entity, dto);
		
		assertNull(entity.getId());
		DeliveryAddressCustomAsserts.assertDeliveryAddressValues(entity);
	}
	
	@Test
	public void copyDeliveryAddressInputDTOToDeliveryAddressShouldCopyValuesCorrectlyWhenUpdating() {
		DeliveryAddressInputDTO dto = DeliveryAddressMockFactory.mockDeliveryAddressInputDTO();
		DeliveryAddress entity = new DeliveryAddress();
		entity.setId(1L);
		
		mapper.copyDeliveryAddressInputDTOToDeliveryAddress(entity, dto);
		
		assertEquals(1L, entity.getId());
		DeliveryAddressCustomAsserts.assertDeliveryAddressValues(entity);
	}
}
