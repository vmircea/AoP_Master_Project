package com.example.aop_master_project.model.entities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j

@Configuration
public class LogsManager {
    public static void info(String message, String param) {
        log.info(message, param);
    }

    public static void info(String message) {
        log.info(message);
    }

    public static void print(String message) {
        System.out.println(message);
    }
}
