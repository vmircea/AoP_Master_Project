package com.example.aop_master_project.repositories;

import com.example.aop_master_project.model.entities.Monitoring;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MonitoringRepository extends JpaRepository<Monitoring, String> {

    Optional<Monitoring> findMonitoringByMethodName(String methodName);
}
