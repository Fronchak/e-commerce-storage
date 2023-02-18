package com.fronchak.ecommercestorage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fronchak.ecommercestorage.entities.Buy;

@Repository
public interface BuyRepository extends JpaRepository<Buy, Long> {

}
