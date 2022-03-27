package com.example.aop_master_project.model.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "Inventory_Stock")
public class InventoryStock {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "amount")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public InventoryStock(String id, int amount, Inventory inventory, Product product) {
        this.id = id;
        this.amount = amount;
        this.inventory = inventory;
        this.product = product;
    }

    public InventoryStock() {
    }

    public String getId() {
        return this.id;
    }

    public int getAmount() {
        return this.amount;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
