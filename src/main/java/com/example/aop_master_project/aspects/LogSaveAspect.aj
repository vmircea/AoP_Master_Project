package com.example.aop_master_project.aspects;

import org.aspectj.lang.Signature;

public aspect LogSaveAspect {

    pointcut logSavingRequest(Object o): args(o)
            && call(* com.example.aop_master_project.services.*.save*(..));

    before(Object o): logSavingRequest(o) {
        System.out.println("Saving " + o + " ...");
    }

    pointcut logNewSave(Object o): args(o) && call (* com.example.aop_master_project.repositories.*.save*(..));

    after(Object o): logNewSave(o) {
        Signature signature = thisJoinPoint.getSignature();
        System.out.println(signature.getName().toUpperCase() + " " + o);
    }
}
