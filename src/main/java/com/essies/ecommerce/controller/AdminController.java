package com.essies.ecommerce.controller;

import com.essies.ecommerce.data.model.Inventory;
import com.essies.ecommerce.data.model.Order;
import com.essies.ecommerce.data.model.Product;
import com.essies.ecommerce.dto.request.AddProductRequest;
import com.essies.ecommerce.dto.request.CreateCategoryRequest;
import com.essies.ecommerce.dto.request.RegisterAdminRequest;
import com.essies.ecommerce.dto.response.*;
import com.essies.ecommerce.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    @PostMapping("/register")
    public ResponseEntity<RegisterAdminResponse> registerAdmin(@RequestBody RegisterAdminRequest registerAdminRequest){
        return ResponseEntity.ok(adminService.registerAdmin(registerAdminRequest));
    }
    @PostMapping("/create_category")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreatedCategoryResponse> createCategory(@RequestBody CreateCategoryRequest createCategoryRequest){
        return ResponseEntity.ok(adminService.createCategory(createCategoryRequest));
    }
    @PostMapping("/add_product")
    public ResponseEntity<AddedProductResponse> addProduct(@RequestBody AddProductRequest addProductRequest){
        return ResponseEntity.ok(adminService.addProduct(addProductRequest));
    }
    @GetMapping("/get_all_products_in_a_category")
    public ResponseEntity<AllProductsInA_CategoryResponse> getAllProductsIn_A_Category(@RequestParam String categoryName){
        return ResponseEntity.ok(adminService.getAllProductsInACategory(categoryName));
    }
    @GetMapping("/find_product_by_id")
    public ResponseEntity<Inventory> findProductsById(@RequestParam Long productId){
        return ResponseEntity.ok(adminService.findByProductId(productId));
    }
    @GetMapping("/find-product-by-name")
    public ResponseEntity<Product> findProductByName(@RequestParam String productName){
        return ResponseEntity.ok(adminService.findProduct(productName));
    }
    @GetMapping("/get_all_products")
    public ResponseEntity<List<Inventory>> getInventory(){
        return ResponseEntity.ok(adminService.getInventory());
    }
    @GetMapping("/check-list-of-orders")
    public ResponseEntity<List<Order>> checkList_OfOrders(){
        return ResponseEntity.ok(adminService.checkListOfOrders());
    }
    @DeleteMapping("/remove_product")
    public ResponseEntity<String> removeProduct(@RequestParam String productName){
        adminService.removeProduct(productName);
        return ResponseEntity.ok("Product Removed Successfully");
    }
    @GetMapping("/process-order")
    public ResponseEntity<ProcessOrderResponse> processOrder(@RequestParam Long orderId){
        return ResponseEntity.ok(adminService.processOrder(orderId));
    }
}
