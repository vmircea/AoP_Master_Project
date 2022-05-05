package com.example.aop_master_project.aop;

import com.example.aop_master_project.model.entities.LogsManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import sun.misc.Signal;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Order(1)
@Component
public class LoggingAspect {

    @Autowired
    private LogsManager logsManager;

    @Pointcut("execution(* com.example.aop_master_project.repositories.*.save*(..)) && args(o)")
    public void logNewSave(Object o) {
    }

    @Pointcut("execution(* com.example.aop_master_project.repositories.*.delete*(..)) && args(o)")
    public void logDelete(Object o) {
    }

    @After("logNewSave(o)")
    public void afterAdvice(JoinPoint joinPoint, Object o) {
        Signature signature = joinPoint.getSignature();
        String message = signature.getName().toUpperCase() + " ";
        logsManager.info(message, o.toString());
    }

    @After("logDelete(o)")
    public void afterAdvice2(JoinPoint joinPoint, Object o) {
        Signature signature = joinPoint.getSignature();
        String message = signature.getName().toUpperCase() + " ";
        logsManager.info(message, o.toString());
    }

    @Before("execution(private * com.example.aop_master_project.services.*.build*(..)) && args(o)")
    public void beforeAdvice(Object o) {
        System.out.println("Mapping object " + o.toString());
    }
    @Around("execution(* com.example.aop_master_project.controllers.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        logsManager.info("Start endpoint {} .", String.valueOf(joinPoint.getSignature()));
        Instant start = Instant.now();
        Object proceed = joinPoint.proceed();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        logsManager.info("End call after {} milliseconds.", String.valueOf(timeElapsed));
        return proceed;
    }
}
