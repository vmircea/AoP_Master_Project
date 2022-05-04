package com.example.aop_master_project.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Order(1)
@Component
@Slf4j
public class LoggingAspect {
    @Around("execution(* com.example.aop_master_project.controllers.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Start endpoint {} .", joinPoint.getSignature());
        Instant start = Instant.now();
        Object proceed = joinPoint.proceed();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        log.info("End call after {} milliseconds.", timeElapsed);
        return proceed;
    }
}
