package com.example.aop_master_project.aop;

import com.example.aop_master_project.model.dto.StockRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
@Slf4j
public class ValidationsAspect {

    @Before("@annotation(com.example.aop_master_project.model.ValidationCheck) && args(st,..)")
    public void validateFields(StockRequest st) {
        if (st.getAmount() < 0) {
            throw new RuntimeException("The amount cannot be a negative value.");
        }
        if (Objects.isNull(st.getInventoryId())) {
            throw new RuntimeException("The inventory cannot be null");
        }
        if (Objects.isNull(st.getProductId())) {
            throw new RuntimeException("The product cannot be null");
        }
    }
}
