package com.example.aop_master_project.model.dto;

import com.example.aop_master_project.model.entities.Inventory;

public class InventoryDto {

    private String id;
    private String inventoryBranch;

    public InventoryDto(String id, String inventoryBranch) {
        this.id = id;
        this.inventoryBranch = inventoryBranch;
    }

    public InventoryDto() {
    }

    public static InventoryDto mapInventoryToDto(final Inventory inventory) {
        return new InventoryDto(inventory.getId(), inventory.getInventoryBranch());
    }

    public String getId() {
        return this.id;
    }

    public String getInventoryBranch() {
        return this.inventoryBranch;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInventoryBranch(String inventoryBranch) {
        this.inventoryBranch = inventoryBranch;
    }
}
