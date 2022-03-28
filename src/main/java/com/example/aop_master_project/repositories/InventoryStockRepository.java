package com.example.aop_master_project.repositories;

import com.example.aop_master_project.model.entities.InventoryStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryStockRepository extends JpaRepository<InventoryStock, String> {
}
