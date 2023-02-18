package com.fronchak.ecommercestorage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fronchak.ecommercestorage.entities.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
