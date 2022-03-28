package com.example.aop_master_project.model.dto;

public class StockRequest {

    private String inventoryId;
    private String productId;
    private int amount;

    public StockRequest(String inventoryId, String productId, int amount) {
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.amount = amount;
    }

    public String getInventoryId() {
        return this.inventoryId;
    }

    public String getProductId() {
        return this.productId;
    }

    public int getAmount() {
        return this.amount;
    }
}
