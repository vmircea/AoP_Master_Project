package com.example.aop_master_project.aspects;

import org.aspectj.lang.Signature;

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
}
