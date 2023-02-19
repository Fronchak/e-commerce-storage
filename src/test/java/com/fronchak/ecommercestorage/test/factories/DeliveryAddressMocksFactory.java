package com.fronchak.ecommercestorage.test.factories;

import java.util.ArrayList;
import java.util.List;

import com.fronchak.ecommercestorage.dtos.delivery_address.DeliveryAddressInputDTO;
import com.fronchak.ecommercestorage.dtos.delivery_address.DeliveryAddressOutputDTO;
import com.fronchak.ecommercestorage.entities.DeliveryAddress;

public class DeliveryAddressMocksFactory {

	public static DeliveryAddress mockDeliveryAddress() {
		return mockDeliveryAddress(0);
	}
	
	public static DeliveryAddress mockDeliveryAddress(int i) {
		DeliveryAddress mock = new DeliveryAddress();
		mock.setId(mockId(i));
		mock.setStreet(mockStreet(i));
		mock.setNumber(mockNumber(i));
		mock.setCep(mockCEP(i));
		return mock;
	}
	
	private static Long mockId(int i) {
		return i + 20L;
	}
	
	private static String mockStreet(int i) {
		return "Mock delivery address street " + i;
	}
	
	private static String mockNumber(int i) {
		return "Mock delivery address number " + i;
	}
	
	private static String mockCEP(int i) {
		return "Mock delivery address cep " + i;
	}
	
	public static DeliveryAddressOutputDTO mockDeliveryAddressOutputDTO() {
		return mockDeliveryAddressOutputDTO(0);
	}
	
	public static DeliveryAddressOutputDTO mockDeliveryAddressOutputDTO(int i) {
		DeliveryAddressOutputDTO mock = new DeliveryAddressOutputDTO();
		mock.setId(mockId(i));
		mock.setStreet(mockStreet(i));
		mock.setNumber(mockNumber(i));
		mock.setCep(mockCEP(i));
		return mock;
	}
	
	public static List<DeliveryAddress> mockDeliveryAddressList() {
		List<DeliveryAddress> list = new ArrayList<>();
		list.add(mockDeliveryAddress(0));
		list.add(mockDeliveryAddress(1));
		return list;
	}
	
	public static List<DeliveryAddressOutputDTO> mockDeliveryAddressOutputDTOList() {
		List<DeliveryAddressOutputDTO> list = new ArrayList<>();
		list.add(mockDeliveryAddressOutputDTO(0));
		list.add(mockDeliveryAddressOutputDTO(1));
		return list;
	}
	
	public static DeliveryAddressInputDTO mockDeliveryAddressInputDTO() {
		DeliveryAddressInputDTO mock = new DeliveryAddressInputDTO();
		mock.setStreet(mockStreet(0));
		mock.setNumber(mockNumber(0));
		mock.setCep(mockCEP(0));
		mock.setIdUser(mockId(0));
		return mock;
	}
}
