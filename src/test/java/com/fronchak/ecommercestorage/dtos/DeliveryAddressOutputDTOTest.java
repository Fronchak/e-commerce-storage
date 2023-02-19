package com.fronchak.ecommercestorage.dtos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.ecommercestorage.dtos.delivery_address.DeliveryAddressOutputDTO;
import com.fronchak.ecommercestorage.entities.DeliveryAddress;
import com.fronchak.ecommercestorage.test.factories.DeliveryAddressMocksFactory;
import com.fronchak.ecommercestorage.test.util.DeliveryAddressCustomAsserts;

@ExtendWith(SpringExtension.class)
public class DeliveryAddressOutputDTOTest {

	@Test
	public void constructorWithDeliveryAddressShouldCreateCorretlyDeliveryAddressOutputDTO() {
		DeliveryAddress entity = DeliveryAddressMocksFactory.mockDeliveryAddress();
		
		DeliveryAddressOutputDTO result = new DeliveryAddressOutputDTO(entity);
		
		DeliveryAddressCustomAsserts.assertDeliveryAddressOutputDTO(result);
	}
}
