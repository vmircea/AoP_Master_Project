package com.example.aop_master_project.services;

import com.example.aop_master_project.model.dto.ProductDto;
import com.example.aop_master_project.model.dto.ProductSaveRequest;
import com.example.aop_master_project.model.entities.Product;
import com.example.aop_master_project.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public List<ProductDto> getProductsBySearchKey(final String searchKey) {
        return productRepository.getProductsByProductNameLikeSearchKey(searchKey)
                .stream()
                .map(ProductDto::convertProductToDto)
                .collect(Collectors.toList());
    }

    public Product getProductById(final String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product could not be found for " + id));
    }
}
