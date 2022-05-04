package com.example.aop_master_project.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "Monitoring")
public class Monitoring {

    @Id
    @Column(name = "method_name")
    public String methodName;

    @Column(name = "calls_number")
    public long callsNumber;

    @Column(name = "execution_total_time")
    public long executionTotalTime;

    public Monitoring(String methodName, long callsNumber, long executionTotalTime) {
        this.methodName = methodName;
        this.callsNumber = callsNumber;
        this.executionTotalTime = executionTotalTime;
    }

    public Monitoring() {
    }

    public String getMethodName() {
        return methodName;
    }

    public long getCallsNumber() {
        return callsNumber;
    }

    public long getExecutionTotalTime() {
        return executionTotalTime;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setCallsNumber(long callsNumber) {
        this.callsNumber = callsNumber;
    }

    public void setExecutionTotalTime(long executionTotalTime) {
        this.executionTotalTime = executionTotalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monitoring that = (Monitoring) o;
        return callsNumber == that.callsNumber && executionTotalTime == that.executionTotalTime && Objects.equals(methodName, that.methodName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(methodName, callsNumber, executionTotalTime);
    }

    @Override
    public String toString() {
        return "Monitoring{" +
                "methodName='" + methodName + '\'' +
                ", callsNumber=" + callsNumber +
                ", executionTotalTime=" + executionTotalTime +
                '}';
    }
}
