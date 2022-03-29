package com.example.aop_master_project.aspects;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.aop_master_project.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

public aspect SecurityAspect {

    pointcut serviceSave():
            call(* com.example.aop_master_project.services.InventoryService.save*(..)) ||
            call(* com.example.aop_master_project.services.InventoryStockService.save*(..)) ||
            call(* com.example.aop_master_project.services.ProductService.save*(..));

    pointcut inventoryUpdate():
            call(* com.example.aop_master_project.services.InventoryStockService.updateStock*(..));


    Object around() : serviceSave() {
        checkRequestToken();
        return proceed();
    }

    Object around() : inventoryUpdate() {
        checkRequestToken();
        return proceed();
    }

    void checkRequestToken() throws ResponseStatusException {
        System.out.println("Checking token...");
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // Get Token from header
        String token = request.getHeader("Authorization");

        if(token == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        try {
            Algorithm algorithm = Algorithm.HMAC256("De4cxZ0vU0ITR5MEAhuAIGyRAn2DS4ekM9Pk2bSs63s=");
            JWTVerifier verifier = JWT.require(algorithm).build();

            verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

}
