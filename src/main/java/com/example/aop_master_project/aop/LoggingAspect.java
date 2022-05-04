package com.example.aop_master_project.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.example.aop_master_project.controllers.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Start endpoint " + joinPoint.getSignature() + ".");
        Instant start = Instant.now();
        Object proceed = joinPoint.proceed();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("End call after " + timeElapsed + " milliseconds.");
        return proceed;
    }
}
