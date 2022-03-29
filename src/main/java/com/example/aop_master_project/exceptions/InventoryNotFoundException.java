package com.example.aop_master_project.exceptions;

public class InventoryNotFoundException extends RuntimeException {

    public InventoryNotFoundException(String msg) {
        super(msg);
    }
}
