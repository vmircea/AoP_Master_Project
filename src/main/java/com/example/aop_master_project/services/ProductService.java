package com.example.aop_master_project.services;

import com.example.aop_master_project.model.dto.ProductSaveRequest;
import com.example.aop_master_project.model.entities.Product;
import com.example.aop_master_project.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saveProduct(ProductSaveRequest request) {
        Product product = new Product(UUID.randomUUID().toString(), request.getProductName(), request.getCategoryName());
        productRepository.save(product);
    }
}
