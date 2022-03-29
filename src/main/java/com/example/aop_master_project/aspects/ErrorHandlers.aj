package com.example.aop_master_project.aspects;

import org.springframework.http.ResponseEntity;

public aspect ErrorHandlers {

    pointcut catchError(RuntimeException e): args(e) && handler(RuntimeException);

    //    after(RuntimeException e) : catchError(e) {
//        System.out.println("Teeeeeeeeeeeeeest");
//        System.out.println(e.getMessage());
//    }

    after() throwing(Throwable e) : execution(* com.example.aop_master_project.controllers.**.*(..)) {
        System.out.println(thisJoinPoint.getSignature());
        System.out.println("Teeeeest");
    }

    ResponseEntity around() : execution(ResponseEntity *(..)) {
        System.out.println("Hello");
        try {
            return proceed();
        } catch (Throwable t) {
            return ResponseEntity.badRequest().body(t.getMessage());
        }
    }

}
