package com.example.aop_master_project.model.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "Inventory")
public class Inventory {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "inventory_branch")
    private String inventoryBranch;

    @OneToMany(mappedBy = "inventory")
    private List<InventoryStock> stocks;

    public Inventory(String id, String inventoryBranch, List<InventoryStock> stocks) {
        this.id = id;
        this.inventoryBranch = inventoryBranch;
        this.stocks = stocks;
    }

    public Inventory() {
    }

    public String getId() {
        return this.id;
    }

    public String getInventoryBranch() {
        return this.inventoryBranch;
    }

    public List<InventoryStock> getStocks() {
        return this.stocks;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInventoryBranch(String inventoryBranch) {
        this.inventoryBranch = inventoryBranch;
    }

    public void setStocks(List<InventoryStock> stocks) {
        this.stocks = stocks;
    }
}
