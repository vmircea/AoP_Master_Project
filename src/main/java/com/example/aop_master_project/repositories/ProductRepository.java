package com.example.aop_master_project.repositories;

import com.example.aop_master_project.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
