package com.example.aop_master_project.aspects;

import org.aspectj.lang.Signature;

import java.time.Duration;
import java.time.Instant;

public aspect LogSaveAspect {

    pointcut logNewSave(Object o): args(o) && call (* com.example.aop_master_project.repositories.*.save*(..));

    pointcut logDelete(Object o): args(o) && call (* com.example.aop_master_project.repositories.*.delete*(..));

    after(Object o): logNewSave(o) {
        Signature signature = thisJoinPoint.getSignature();
        System.out.println(signature.getName().toUpperCase() + " " + o);
    }

    after(Object o): logDelete(o) {
        Signature signature = thisJoinPoint.getSignature();
        System.out.println(signature.getName().toUpperCase() + " " + o);
    }

    Object around(): execution(* com.example.aop_master_project.controllers.**.*(..)) {
        System.out.println("Start endpoint " + thisJoinPoint.getSignature() + ".");
        Instant start = Instant.now();
        Object proceed = proceed();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("End call after " + timeElapsed + " milliseconds.");
        return proceed;
    }

    before(Object o) : args(o) && execution (private * com.example.aop_master_project.services.**.build*(..)) {
        System.out.println("Mapping object " + o.toString());
    }
}
