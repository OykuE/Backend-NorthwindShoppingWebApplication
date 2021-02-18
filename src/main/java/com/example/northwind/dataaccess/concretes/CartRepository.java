package com.example.northwind.dataaccess.concretes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.northwind.entities.concretes.Cart;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer>{
    Optional<Cart> findByCustomerId(String customerId);
    void deleteByCustomerId(String customerId);
    boolean existsByCustomerId(String customerId);
}
