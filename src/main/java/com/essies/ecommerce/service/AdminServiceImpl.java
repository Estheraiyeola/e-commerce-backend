package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.Admin;
import com.essies.ecommerce.data.model.Inventory;
import com.essies.ecommerce.data.model.Order;
import com.essies.ecommerce.data.model.Product;
import com.essies.ecommerce.data.repository.AdminRepository;
import com.essies.ecommerce.dto.request.AddProductRequest;
import com.essies.ecommerce.dto.request.CreateCategoryRequest;
import com.essies.ecommerce.dto.request.RegisterAdminRequest;
import com.essies.ecommerce.dto.response.*;
import com.essies.ecommerce.exception.AdminAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.essies.ecommerce.util.Mapper.map;
@AllArgsConstructor
@Service
public class AdminServiceImpl implements AdminService{
    private final AdminRepository adminRepository;
    private final CategoryService categoryService;
    private final ProductService productService;

    private final InventoryService inventoryService;
    private final OrderService orderService;
    @Override
    public RegisterAdminResponse registerAdmin(RegisterAdminRequest registerAdminRequest) {
        Admin foundAdmin = adminRepository.findByEmail(registerAdminRequest.getEmail());
        if(foundAdmin == null){
            Admin admin = map(registerAdminRequest);
            adminRepository.save(admin);

            RegisterAdminResponse registerAdminResponse = new RegisterAdminResponse();
            registerAdminResponse.setEmail(admin.getEmail());
            registerAdminResponse.setPassword(admin.getPassword());
            registerAdminResponse.setUsername(admin.getUsername());
            registerAdminResponse.setStatus("Successful");
            registerAdminResponse.setRole(admin.getRole());
            return registerAdminResponse;
        }
        throw new AdminAlreadyExistsException("Admin already exists");
    }

    @Override
    public CreatedCategoryResponse createCategory(CreateCategoryRequest categoryRequest) {
        CreatedCategoryResponse response = categoryService.createCategory(categoryRequest);
        return response;
    }

    @Override
    public AddedProductResponse addProduct(AddProductRequest addProductRequest) {
        return productService.addProduct(addProductRequest);
    }

    @Override
    public AllProductsInA_CategoryResponse getAllProductsInACategory(String category) {
        return productService.getProductsInA_Category(category);
    }

    @Override
    public Inventory findByProductId(Long id) {
        return inventoryService.findByProductId(id);
    }

    @Override
    public List<Inventory> getInventory() {
        return inventoryService.getAllInventory();
    }

    @Override
    public void removeProduct(String name) {
        productService.removeProduct(name);
    }

    @Override
    public Product findProduct(String name) {
        return productService.findProduct(name);
    }

    @Override
    public List<Order> checkListOfOrders() {
        return orderService.checkListOfOrders();
    }

    @Override
    public ProcessOrderResponse processOrder(Long orderId) {
        return orderService.processOrder(orderId);
    }


}
