package com.example.aop_master_project.aop;

import com.example.aop_master_project.model.entities.Monitoring;
import com.example.aop_master_project.repositories.MonitoringRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Order(2)
@Component
@Slf4j
public class MonitoringAspect {

    private final MonitoringRepository monitoringRepository;

    public MonitoringAspect(MonitoringRepository monitoringRepository) {
        this.monitoringRepository = monitoringRepository;
    }

    @Around("execution(* com.example.aop_master_project.controllers.*.*(..))")
    public Object monitoringTool(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant start = Instant.now();
        Object proceed = joinPoint.proceed();
        Instant finish = Instant.now();

        final String methodName = joinPoint.getSignature().getName();
        final long timeElapsed = Duration.between(start, finish).toMillis();

        Monitoring monitoring = monitoringRepository.findMonitoringByMethodName(methodName)
                .orElseGet(() -> new Monitoring(methodName, 0, 0));

        monitoring.setCallsNumber(monitoring.getCallsNumber() + 1);
        monitoring.setExecutionTotalTime(monitoring.getExecutionTotalTime() + timeElapsed);

        monitoringRepository.save(monitoring);

        return proceed;
    }
}
