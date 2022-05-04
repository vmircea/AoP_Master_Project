package com.example.aop_master_project.aop;

import com.example.aop_master_project.exceptions.InventoryNotFoundException;
import com.example.aop_master_project.exceptions.NotInStockException;
import com.example.aop_master_project.exceptions.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ErrorHandlingAspect {

    @Around("execution(* com.example.aop_master_project.controllers.*.*(..))")
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
        log.info("Exception thrown: {}", e.getMessage());
    }

}
