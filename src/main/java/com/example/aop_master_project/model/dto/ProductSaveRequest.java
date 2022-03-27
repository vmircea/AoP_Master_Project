package com.example.aop_master_project.model.dto;

public class ProductSaveRequest {

    private String productName;
    private String categoryName;

    public ProductSaveRequest(String productName, String categoryName) {
        this.productName = productName;
        this.categoryName = categoryName;
    }

    public ProductSaveRequest() {
    }

    public String getProductName() {
        return this.productName;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String toString() {
        return "product '" + this.getProductName() + "' from category '" + this.getCategoryName() + "'";
    }
}
