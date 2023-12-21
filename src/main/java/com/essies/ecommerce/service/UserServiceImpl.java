package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.*;
import com.essies.ecommerce.data.repository.UserRepository;
import com.essies.ecommerce.dto.request.AddToCartRequest;
import com.essies.ecommerce.dto.request.ProductReviewRequest;
import com.essies.ecommerce.dto.request.RegisterUserRequest;
import com.essies.ecommerce.dto.response.ProductReviewResponse;
import com.essies.ecommerce.dto.response.RegisteredUserResponse;
import com.essies.ecommerce.dto.response.RemovedProductsCartResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final AddressService addressService;
    private final UserRepository userRepository;
    private final ProductService productService;
    private final CartService cartService;
    @Override
    public RegisteredUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        User user = new User();
        Address savedAddress = addressService.addAddress(registerUserRequest.getAddress(), user);
        user.setEmail(registerUserRequest.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(registerUserRequest.getPassword());
        user.setPassword(password);
        user.setRole(Role.USER);
        user.setFirstName(registerUserRequest.getFirstName());
        user.setUsername(registerUserRequest.getUsername());
        user.setLastName(registerUserRequest.getLastName());
        user.setPhoneNumber(registerUserRequest.getPhoneNumber());
        user.setAddress(savedAddress);


        User savedUser = userRepository.save(user);


        RegisteredUserResponse response = new RegisteredUserResponse();
        response.setPassword(savedUser.getPassword());
        response.setStatus("Successful");
        response.setUsername(savedUser.getUsername());
        response.setUserId(savedUser.getId());
        return response;
    }





    @Override
    public Product findProduct(String productName) {
        return productService.findProduct(productName);
    }

    @Override
    public List<Product> findProductsByCategoryName(String categoryName) {
        return productService.findProductsByCategoryName(categoryName);
    }

    @Override
    public Cart addProductToCart(List<AddToCartRequest> addToCartRequestList, Long userId) {
        return cartService.addToCart(addToCartRequestList, userId);
    }

    @Override
    public User findUserBy(Long userId) {
        return userRepository.findUserById(userId);
    }

    @Override
    public List<Cart> removeProductFromCartBy(String productName) {
        return cartService.removeProductFromCart(productName);
    }

    @Override
    public ProductReviewResponse productReview(ProductReviewRequest productReviewRequest, Long userId) {
        return productService.addReview(productReviewRequest, userId);
    }
}
