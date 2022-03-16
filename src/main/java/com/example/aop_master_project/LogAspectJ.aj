package com.example.aop_master_project;

public aspect LogAspectJ {
    void around(): call(void TestController.printHello()) {
        System.out.println("Log before...");
        proceed();
        System.out.println("After log...");
    }
}