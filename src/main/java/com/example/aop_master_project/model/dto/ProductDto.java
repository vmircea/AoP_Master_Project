package com.example.aop_master_project.model.dto;

import com.example.aop_master_project.model.entities.Product;

public class ProductDto {

    private String id;
    private String productName;
    private String categoryName;

    public static ProductDto convertProductToDto(Product product) {
        return new ProductDto(product.getId(), product.getProductName(), product.getCategoryName());
    }

    public ProductDto(String id, String productName, String categoryName) {
        this.id = id;
        this.productName = productName;
        this.categoryName = categoryName;
    }

    public ProductDto() {
    }

    public String getId() {
        return this.id;
    }

    public String getProductName() {
        return this.productName;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
