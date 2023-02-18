package com.fronchak.ecommercestorage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fronchak.ecommercestorage.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
