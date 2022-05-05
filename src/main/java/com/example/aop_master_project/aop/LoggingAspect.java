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

    @Pointcut("execution(* com.example.aop_master_project.services.UserDataService.saveUser(..))")
    public void logUserSaved() {
    }

    @After("logNewSave(o)")
    public void afterAdvice(JoinPoint joinPoint, Object o) {
        Signature signature = joinPoint.getSignature();
        logsManager.print("Saving Item New Inventory");
        String message = signature.getName().toUpperCase() + " {}";
        logsManager.info(message, o.toString());
    }

    @After("logDelete(o)")
    public void afterAdvice2(JoinPoint joinPoint, Object o) {
        Signature signature = joinPoint.getSignature();
        logsManager.print("Deleted item from List");
        String message = signature.getName().toUpperCase() + " {}";
        logsManager.info(message, o.toString());
    }

    @Before("logUserSaved()")
    public void beforeAdvice1(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        logsManager.print("Attempt to save user");
        String message = signature.getName().toUpperCase();
        logsManager.info(message);
    }

    @After("logUserSaved()")
    public void afterAdvice3(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        logsManager.print("Saved New User");
        String message = signature.getName().toUpperCase();
        logsManager.info(message);
    }

//    @Before("execution(private * com.example.aop_master_project.services.*.build*(..)) && args(o)")
//    public void beforeAdvice(Object o) {
//        logsManager.print("Mapping object " + o.toString());
//    }

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
