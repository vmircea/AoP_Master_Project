package com.example.aop_master_project.services;

import com.example.aop_master_project.exceptions.InventoryNotFoundException;
import com.example.aop_master_project.model.dto.InventoryDto;
import com.example.aop_master_project.model.dto.ProductDto;
import com.example.aop_master_project.model.dto.StockResponse;
import com.example.aop_master_project.model.entities.Inventory;
import com.example.aop_master_project.model.entities.InventoryStock;
import com.example.aop_master_project.repositories.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public InventoryDto saveInventory(final String inventoryName) {
        Optional<Inventory> similarInventory = inventoryRepository.findAll().stream()
                .filter(i -> i.getInventoryBranch().equals(inventoryName))
                .findFirst();
        if (similarInventory.isPresent()) {
            throw new RuntimeException("Inventory with name " + inventoryName + " already exists");
        }
        Inventory inventory = new Inventory(UUID.randomUUID().toString(), inventoryName, List.of());
        Inventory saved = inventoryRepository.save(inventory);
        return InventoryDto.mapInventoryToDto(saved);
    }

    public List<InventoryDto> getAllInventories() {
        return inventoryRepository.findAll().stream()
                .map(InventoryDto::mapInventoryToDto)
                .collect(Collectors.toList());
    }

    public List<StockResponse> getAllStocksOfInventory(final String inventoryId) {
        Inventory inventory = this.getById(inventoryId);
        return getStockResponses(inventory);
    }

    public List<StockResponse> getStockResponses(Inventory inventory) {
        return inventory.getStocks().stream()
                .map(this::buildStockResponseFromStock)
                .collect(Collectors.toList());
    }

    public Inventory getById(final String id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory could not be found for " + id));
    }

    private StockResponse buildStockResponseFromStock(InventoryStock inventoryStock) {
        ProductDto productDto = ProductDto.convertProductToDto(inventoryStock.getProduct());
        return new StockResponse(productDto, inventoryStock.getAmount());
    }
}
