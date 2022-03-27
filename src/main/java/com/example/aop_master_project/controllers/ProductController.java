package com.example.aop_master_project.controllers;

import com.example.aop_master_project.model.dto.ProductSaveRequest;
import com.example.aop_master_project.services.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void saveProduct(@RequestBody ProductSaveRequest request) {
        productService.saveProduct(request);
    }
}
