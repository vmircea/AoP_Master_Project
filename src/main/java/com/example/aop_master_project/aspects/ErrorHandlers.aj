package com.example.aop_master_project.aspects;

import com.example.aop_master_project.exceptions.InventoryNotFoundException;
import com.example.aop_master_project.exceptions.NotInStockException;
import com.example.aop_master_project.exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;

public aspect ErrorHandlers {

    after() throwing(Throwable t) : execution(* com.example.aop_master_project.controllers.**.*(..)) {
        System.out.println("Exception on " + thisJoinPoint.getSignature().getName() + ". Error message: " + t.getMessage());
    }

    ResponseEntity around() : execution(ResponseEntity *(..)) {
        try {
            return proceed();
        } catch (NotInStockException | InventoryNotFoundException | ProductNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Throwable t) {
            return ResponseEntity.internalServerError().body(t.getMessage());
        }
    }

}
