package com.example.aop_master_project.model.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "Product")
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "category_name")
    private String categoryName;

    public Product(String id, String productName, String categoryName) {
        this.id = id;
        this.productName = productName;
        this.categoryName = categoryName;
    }

    public Product() {
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

    public String toString() {
        return "product '" + this.productName + "' with category '" + this.categoryName + "' and new ID: " + this.getId();
    }
}
