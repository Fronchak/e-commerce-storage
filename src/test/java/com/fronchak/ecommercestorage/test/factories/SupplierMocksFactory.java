package com.fronchak.ecommercestorage.test.factories;

import java.util.ArrayList;
import java.util.List;

import com.fronchak.ecommercestorage.dtos.supplier.SupplierInputDTO;
import com.fronchak.ecommercestorage.dtos.supplier.SupplierInsertDTO;
import com.fronchak.ecommercestorage.dtos.supplier.SupplierOutputDTO;
import com.fronchak.ecommercestorage.dtos.supplier.SupplierUpdateDTO;
import com.fronchak.ecommercestorage.entities.Supplier;

public class SupplierMocksFactory {

	public static Supplier mockSupplierEntity() {
		return mockSupplierEntity(0);
	}
	
	public static Supplier mockSupplierEntity(int i) {
		Supplier mock = new Supplier();
		mock.setId(mockSupplierId(i));
		mock.setName(mockSupplierName(i));
		return mock;
	}
	
	private static Long mockSupplierId(int i) {
		return i + 0L;
	}
	
	private static String mockSupplierName(int i) {
		return "Mock supplier name " + i;
	}
	
	public static SupplierOutputDTO mockSupplierOutputDTO() {
		return mockSupplierOutputDTO(0);
	}
	
	public static SupplierOutputDTO mockSupplierOutputDTO(int i) {
		SupplierOutputDTO mock = new SupplierOutputDTO();
		mock.setId(mockSupplierId(i));
		mock.setName(mockSupplierName(i));
		return mock;
	}
	
	public static List<Supplier> mockSupplierEntityList() {
		List<Supplier> list = new ArrayList<>();
		list.add(mockSupplierEntity(0));
		list.add(mockSupplierEntity(1));
		return list;
	}
	
	public static List<SupplierOutputDTO> mockSupplierOutputDTOList() {
		List<SupplierOutputDTO> list = new ArrayList<>();
		list.add(mockSupplierOutputDTO(0));
		list.add(mockSupplierOutputDTO(1));
		return list;
	}
	
	public static SupplierInsertDTO mockSupplierInsertDTO() {
		return (SupplierInsertDTO) mockSupplierInputDTO(new SupplierInsertDTO(), 0);
	}	
	
	private static SupplierInputDTO mockSupplierInputDTO(SupplierInputDTO mock, int i) {
		mock.setName(mockSupplierName(i));
		return mock;
	}
	
	public static SupplierUpdateDTO mockSupplierUpdateDTO() {
		return (SupplierUpdateDTO) mockSupplierInputDTO(new SupplierUpdateDTO(), 0);
	}
}
