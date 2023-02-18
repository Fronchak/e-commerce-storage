package com.fronchak.ecommercestorage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fronchak.ecommercestorage.entities.Sell;

@Repository
public interface SellRepository extends JpaRepository<Sell, Long> {

}
