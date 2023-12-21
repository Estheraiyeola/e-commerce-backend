package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.Inventory;
import com.essies.ecommerce.data.repository.InventoryRepository;
import com.essies.ecommerce.dto.request.CreateInventoryRequest;
import com.essies.ecommerce.dto.response.CreatedInventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService{
    @Autowired
    private InventoryRepository inventoryRepository;
    @Override
    public CreatedInventoryResponse addProductToInventory(CreateInventoryRequest createInventoryRequest) {
        Inventory inventory = new Inventory();
        inventory.setProductId(createInventoryRequest.getProductId());
        inventory.setLastRestockedDate(createInventoryRequest.getLastRestockedDate());
        inventory.setStockQuantity(createInventoryRequest.getStockQuantity());
        inventoryRepository.save(inventory);

        CreatedInventoryResponse createdInventoryResponse = new CreatedInventoryResponse();
        createdInventoryResponse.setStatus("Successful");
        createdInventoryResponse.setProductId(inventory.getProductId());
        createdInventoryResponse.setStockQuantity(inventory.getStockQuantity());
        createdInventoryResponse.setLastRestockedDate(inventory.getLastRestockedDate());
        return createdInventoryResponse;
    }

    @Override
    public Inventory findByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId);
    }

    @Override
    public void deleteAll() {
        inventoryRepository.deleteAll();
    }

    @Override
    public List<Inventory> getAllInventory() {
        return new ArrayList<>(inventoryRepository.findAll());
    }
}
