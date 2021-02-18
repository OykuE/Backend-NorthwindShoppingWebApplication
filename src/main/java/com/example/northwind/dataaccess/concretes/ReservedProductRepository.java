package com.example.northwind.dataaccess.concretes;

import com.example.northwind.entities.concretes.ReservedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservedProductRepository extends JpaRepository<ReservedProduct, Integer> {
}
