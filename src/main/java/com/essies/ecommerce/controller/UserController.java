package com.essies.ecommerce.controller;

import com.essies.ecommerce.data.model.Cart;
import com.essies.ecommerce.data.model.Product;
import com.essies.ecommerce.data.model.User;
import com.essies.ecommerce.dto.request.AddToCartRequest;
import com.essies.ecommerce.dto.request.ProductReviewRequest;
import com.essies.ecommerce.dto.request.RegisterUserRequest;
import com.essies.ecommerce.dto.response.ProcessOrderResponse;
import com.essies.ecommerce.dto.response.ProductReviewResponse;
import com.essies.ecommerce.dto.response.RegisteredUserResponse;
import com.essies.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisteredUserResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest){
        return ResponseEntity.ok(userService.registerUser(registerUserRequest));
    }
    @GetMapping("/find-product-by-name")
    public ResponseEntity<Product> findProductByName(@RequestParam String productName){
        return ResponseEntity.ok(userService.findProduct(productName));
    }
    @PostMapping("/find-products-by-category")
    public ResponseEntity<List<Product>> findProductsByCategoryName(@RequestParam String categoryName){
        return ResponseEntity.ok(userService.findProductsByCategoryName(categoryName));
    }
    @PostMapping("add-to-cart")
    public ResponseEntity<Cart> addProductToCart(@RequestBody List<AddToCartRequest> addToCartRequestList,@RequestParam Long userId){
        return ResponseEntity.ok(userService.addProductToCart(addToCartRequestList, userId));
    }
    @GetMapping("find-user")
    public ResponseEntity<User> findUserBy(@RequestParam Long userId){
        return ResponseEntity.ok(userService.findUserBy(userId));
    }
    @DeleteMapping("remove-product-from-cart")
    public ResponseEntity<List<Cart>> removeProductFromCartBy(@RequestParam String productName){
        return ResponseEntity.ok(userService.removeProductFromCartBy(productName));
    }
    @PostMapping("product-review")
    public ResponseEntity<ProductReviewResponse> productReview(@RequestBody ProductReviewRequest productReviewRequest, @RequestParam Long userId,@RequestBody Cart cartResponse){
        return ResponseEntity.ok(userService.productReview(productReviewRequest, userId, cartResponse));
    }

}
