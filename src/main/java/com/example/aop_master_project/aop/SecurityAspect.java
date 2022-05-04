package com.example.aop_master_project.aop;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class SecurityAspect {

    @Around(value = "execution(* com.example.aop_master_project.services.InventoryService.save*(..)) ||" +
            "execution(* com.example.aop_master_project.services.InventoryStockService.save*(..)) ||" +
            "execution(* com.example.aop_master_project.services.ProductService.save*(..))")
    private Object checkTokenBeforeSaving(ProceedingJoinPoint pjp) throws Throwable {
        checkRequestToken();
        return pjp.proceed();
    }

    @Around(value = "execution(* com.example.aop_master_project.services.InventoryStockService.updateStock*(..))")
    private Object checkTokenBeforeInventoryUpdate(ProceedingJoinPoint pjp) throws Throwable {
        checkRequestToken();
        return pjp.proceed();
    }

    void checkRequestToken() throws ResponseStatusException {
        System.out.println("[SpringAOP] Checking token...");
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
