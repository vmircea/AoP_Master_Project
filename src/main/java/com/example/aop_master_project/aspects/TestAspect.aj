package com.example.aop_master_project.aspects;

public aspect TestAspect {

    pointcut printHello() : execution (* com.example.aop_master_project.controllers.TestController.printHello(..));

    void around(): printHello() {
        System.out.println("Log before...");
        proceed();
        System.out.println("After log...");
    }

}