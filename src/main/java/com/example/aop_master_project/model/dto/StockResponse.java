package com.example.aop_master_project.model.dto;

public class StockResponse {

    private ProductDto product;
    private int amount;

    public StockResponse(ProductDto product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public StockResponse() {
    }

    public ProductDto getProduct() {
        return this.product;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
