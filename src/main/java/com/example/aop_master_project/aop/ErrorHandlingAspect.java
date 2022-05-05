package com.example.aop_master_project.aop;

import com.example.aop_master_project.exceptions.InventoryNotFoundException;
import com.example.aop_master_project.exceptions.NotInStockException;
import com.example.aop_master_project.exceptions.ProductNotFoundException;
import com.example.aop_master_project.model.entities.LogsManager;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ErrorHandlingAspect {

    @Autowired
    private LogsManager logsManager;

    @Around("execution(org.springframework.http.ResponseEntity com.example.aop_master_project.controllers.*.*(..))")
    public ResponseEntity handleExceptions(ProceedingJoinPoint joinPoint) {
        try {
            return (ResponseEntity) joinPoint.proceed();
        } catch (NotInStockException | InventoryNotFoundException | ProductNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Throwable t) {
            return ResponseEntity.internalServerError().body(t.getMessage());
        }
    }

    @AfterThrowing (pointcut = "execution (* com.example.aop_master_project.*.*.*(..))", throwing = "e")
    public void logAfterThrowing(Throwable e) {
        logsManager.info("Exception thrown: {}", e.getMessage());
    }

}
