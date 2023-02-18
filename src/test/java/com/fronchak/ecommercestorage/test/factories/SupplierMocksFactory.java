package com.fronchak.ecommercestorage.test.factories;

import java.util.ArrayList;
import java.util.List;

import com.fronchak.ecommercestorage.dtos.supplier.SupplierInputDTO;
import com.fronchak.ecommercestorage.dtos.supplier.SupplierInsertDTO;
import com.fronchak.ecommercestorage.dtos.supplier.SupplierOutputDTO;
import com.fronchak.ecommercestorage.dtos.supplier.SupplierUpdateDTO;
import com.fronchak.ecommercestorage.entities.Supplier;

public class SupplierMocksFactory {

	public static Supplier mockSupplier() {
		return mockSupplier(0);
	}
	
	public static Supplier mockSupplier(int i) {
		Supplier mock = new Supplier();
		mock.setId(mockId(i));
		mock.setName(mockName(i));
		return mock;
	}
	
	private static Long mockId(int i) {
		return i + 0L;
	}
	
	private static String mockName(int i) {
		return "Mock supplier name " + i;
	}
	
	public static SupplierOutputDTO mockSupplierOutputDTO() {
		return mockSupplierOutputDTO(0);
	}
	
	public static SupplierOutputDTO mockSupplierOutputDTO(int i) {
		SupplierOutputDTO mock = new SupplierOutputDTO();
		mock.setId(mockId(i));
		mock.setName(mockName(i));
		return mock;
	}
	
	public static List<Supplier> mockSupplierList() {
		List<Supplier> list = new ArrayList<>();
		list.add(mockSupplier(0));
		list.add(mockSupplier(1));
		return list;
	}
	
	public static List<SupplierOutputDTO> mockSupplierOutputDTOList() {
		List<SupplierOutputDTO> list = new ArrayList<>();
		list.add(mockSupplierOutputDTO(0));
		list.add(mockSupplierOutputDTO(1));
		return list;
	}
	
	public static SupplierInsertDTO mockSupplierInsertDTO() {
		return (SupplierInsertDTO) mockSupplierInputDTO(new SupplierInsertDTO());
	}	
	
	private static SupplierInputDTO mockSupplierInputDTO(SupplierInputDTO mock) {
		mock.setName(mockName(0));
		return mock;
	}
	
	public static SupplierUpdateDTO mockSupplierUpdateDTO() {
		return (SupplierUpdateDTO) mockSupplierInputDTO(new SupplierUpdateDTO());
	}
}
