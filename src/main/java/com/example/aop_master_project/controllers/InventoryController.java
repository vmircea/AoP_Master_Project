package com.example.aop_master_project.controllers;

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
    public void createInventory(@RequestParam String inventoryName) {
        inventoryService.saveInventory(inventoryName);
    }

    @PutMapping("/{inventoryId}/add/product")
    public List<StockResponse> addProductsToInventoryStockAndUpdateAmount(@PathVariable String inventoryId,
                                                                          @RequestParam String productId,
                                                                          @RequestParam int amount) {
        StockRequest stockRequest = new StockRequest(inventoryId, productId, amount);
        return inventoryStockService.updateStockByAddingProductAmount(stockRequest);
    }

    @PutMapping("/{inventoryId}/remove/product")
    public ResponseEntity<List<StockResponse>> removeProductsFromInventoryStockAndUpdateAmount(@PathVariable String inventoryId,
                                                                          @RequestParam String productId,
                                                                          @RequestParam int amount) {
        StockRequest stockRequest = new StockRequest(inventoryId, productId, amount);
        var list = inventoryStockService.updateStockByRemovingProductAmount(stockRequest);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{inventoryId}/stocks")
    public List<StockResponse> getInventoryStocks(@PathVariable String inventoryId) {
        return inventoryService.getAllStocksOfInventory(inventoryId);
    }
}
