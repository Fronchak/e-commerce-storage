package com.fronchak.ecommercestorage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fronchak.ecommercestorage.entities.DeliveryAddress;

@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {

}
