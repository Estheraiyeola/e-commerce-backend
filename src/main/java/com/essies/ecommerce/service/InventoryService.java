package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.Inventory;
import com.essies.ecommerce.dto.request.CreateInventoryRequest;
import com.essies.ecommerce.dto.response.CreatedInventoryResponse;

import java.util.List;

public interface InventoryService {
    CreatedInventoryResponse addProductToInventory(CreateInventoryRequest createInventoryRequest);

    Inventory findByProductId(Long productId);

    void deleteAll();

    List<Inventory> getAllInventory();
}
