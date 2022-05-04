package com.example.aop_master_project.controllers;

import com.example.aop_master_project.model.dto.ProductDto;
import com.example.aop_master_project.model.dto.ProductSaveRequest;
import com.example.aop_master_project.services.ProductService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> getProductsForDropdown(@RequestParam(required = false, defaultValue = "") String searchKey) {
        return ResponseEntity.ok(productService.getProductsBySearchKey(searchKey));
    }
}
