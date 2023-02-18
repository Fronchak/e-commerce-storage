package com.fronchak.ecommercestorage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fronchak.ecommercestorage.entities.DeliveryAddress;
import com.fronchak.ecommercestorage.entities.User;

@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {

	List<DeliveryAddress> findAllByUser(User user);
}
