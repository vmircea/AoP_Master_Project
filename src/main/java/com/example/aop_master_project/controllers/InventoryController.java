package com.example.aop_master_project.controllers;

import com.example.aop_master_project.model.dto.InventoryDto;
import com.example.aop_master_project.model.dto.StockRequest;
import com.example.aop_master_project.model.dto.StockResponse;
import com.example.aop_master_project.services.InventoryService;
import com.example.aop_master_project.services.InventoryStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    private final InventoryStockService inventoryStockService;

    public InventoryController(InventoryService inventoryService, InventoryStockService inventoryStockService) {
        this.inventoryService = inventoryService;
        this.inventoryStockService = inventoryStockService;
    }

    @PostMapping
    public ResponseEntity<InventoryDto> createInventory(@RequestParam String inventoryName) {
        return ResponseEntity.ok(inventoryService.saveInventory(inventoryName));
    }

    @GetMapping("/list")
    public ResponseEntity<List<InventoryDto>> getAllInventoriesForDropdown() {
        return ResponseEntity.ok(inventoryService.getAllInventories());
    }

    @PutMapping("/{inventoryId}/add/product")
    public ResponseEntity<List<StockResponse>> addProductsToInventoryStockAndUpdateAmount(@PathVariable String inventoryId,
                                                                          @RequestParam String productId,
                                                                          @RequestParam int amount) {
        StockRequest stockRequest = new StockRequest(inventoryId, productId, amount);
        return ResponseEntity.ok(inventoryStockService.updateStockByAddingProductAmount(stockRequest));
    }

    @PutMapping("/{inventoryId}/remove/product")
    public ResponseEntity<List<StockResponse>> removeProductsFromInventoryStockAndUpdateAmount(@PathVariable String inventoryId,
                                                                          @RequestParam String productId,
                                                                          @RequestParam int amount) {
        StockRequest stockRequest = new StockRequest(inventoryId, productId, amount);
        return ResponseEntity.ok(inventoryStockService.updateStockByRemovingProductAmount(stockRequest));
    }

    @GetMapping("/{inventoryId}/stocks")
    public ResponseEntity<List<StockResponse>> getInventoryStocks(@PathVariable String inventoryId) {
        return ResponseEntity.ok(inventoryService.getAllStocksOfInventory(inventoryId));
    }
}
