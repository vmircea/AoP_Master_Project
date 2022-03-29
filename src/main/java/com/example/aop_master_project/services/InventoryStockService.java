package com.example.aop_master_project.services;

import com.example.aop_master_project.model.dto.StockRequest;
import com.example.aop_master_project.model.dto.StockResponse;
import com.example.aop_master_project.model.entities.Inventory;
import com.example.aop_master_project.model.entities.InventoryStock;
import com.example.aop_master_project.model.entities.Product;
import com.example.aop_master_project.repositories.InventoryStockRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryStockService {

    private final InventoryStockRepository inventoryStockRepository;
    private final ProductService productService;
    private final InventoryService inventoryService;

    public InventoryStockService(InventoryStockRepository inventoryStockRepository,
                                 ProductService productService,
                                 InventoryService inventoryService) {
        this.inventoryStockRepository = inventoryStockRepository;
        this.productService = productService;
        this.inventoryService = inventoryService;
    }

    @Transactional
    public List<StockResponse> updateStockByAddingProductAmount(StockRequest stockRequest) {
        Inventory inventory = inventoryService.getById(stockRequest.getInventoryId());
        Product product = productService.getProductById(stockRequest.getProductId());

        Optional<InventoryStock> stockOpt = inventory.getStocks().stream()
                .filter(s -> s.getProduct().equals(product))
                .findFirst();

        InventoryStock inventoryStock;
        if (stockOpt.isPresent()) {
            inventoryStock = stockOpt.get();
            inventoryStock.setAmount(inventoryStock.getAmount() + stockRequest.getAmount());
        } else {
            inventoryStock = new InventoryStock(UUID.randomUUID().toString(), stockRequest.getAmount(), inventory, product);
            inventory.getStocks().add(inventoryStock);
        }
        inventoryStockRepository.save(inventoryStock);
        return inventoryService.getStockResponses(inventory);
    }

    @Transactional
    public List<StockResponse> updateStockByRemovingProductAmount(StockRequest stockRequest) {
        Inventory inventory = inventoryService.getById(stockRequest.getInventoryId());
        Product product = productService.getProductById(stockRequest.getProductId());

        InventoryStock stock = inventory.getStocks().stream()
                .filter(s -> s.getProduct().equals(product))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product could not be found in the stock"));

        int amount = stock.getAmount() - stockRequest.getAmount();
        if (amount <= 0) {
            inventory.getStocks().remove(stock);
            inventoryStockRepository.delete(stock);
        } else {
            stock.setAmount(amount);
            inventoryStockRepository.save(stock);
        }
        return inventoryService.getStockResponses(inventory);
    }
}