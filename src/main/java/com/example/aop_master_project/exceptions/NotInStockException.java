package com.example.aop_master_project.exceptions;

public class NotInStockException extends RuntimeException {

    public NotInStockException(String msg) {
        super(msg);
    }
}
