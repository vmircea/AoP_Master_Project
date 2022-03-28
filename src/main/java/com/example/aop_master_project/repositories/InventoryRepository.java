package com.example.aop_master_project.repositories;

import com.example.aop_master_project.model.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, String> {
}
