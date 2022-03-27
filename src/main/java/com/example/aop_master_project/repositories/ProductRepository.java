package com.example.aop_master_project.repositories;

import com.example.aop_master_project.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT p FROM Product p " +
            "WHERE (:productName = '' OR p.productName LIKE CONCAT('%', :productName, '%'))")
    List<Product> getProductsByProductNameLikeSearchKey(@Param(value = "productName") String productName);
}
