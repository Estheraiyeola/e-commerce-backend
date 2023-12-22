package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.Inventory;
import com.essies.ecommerce.data.model.Order;
import com.essies.ecommerce.data.model.Product;
import com.essies.ecommerce.dto.request.AddProductRequest;
import com.essies.ecommerce.dto.request.CreateCategoryRequest;
import com.essies.ecommerce.dto.request.RegisterAdminRequest;
import com.essies.ecommerce.dto.response.*;

import java.util.List;

public interface AdminService {
    RegisterAdminResponse registerAdmin(RegisterAdminRequest registerAdminRequest);

    CreatedCategoryResponse createCategory(CreateCategoryRequest categoryRequest);

    AddedProductResponse addProduct(AddProductRequest addProductRequest);

    AllProductsInA_CategoryResponse getAllProductsInACategory(String category);

    Inventory findByProductId(Long id);

    List<Inventory> getInventory();

    void removeProduct(String name);

    Product findProduct(String name);

    List<Order> checkListOfOrders();

    ProcessOrderResponse processOrder(Long orderId);
}
