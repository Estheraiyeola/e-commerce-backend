package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.Cart;
import com.essies.ecommerce.data.model.Product;
import com.essies.ecommerce.data.model.User;
import com.essies.ecommerce.dto.request.AddToCartRequest;
import com.essies.ecommerce.dto.request.ProductReviewRequest;
import com.essies.ecommerce.dto.request.RegisterUserRequest;
import com.essies.ecommerce.dto.response.ProductReviewResponse;
import com.essies.ecommerce.dto.response.RegisteredUserResponse;

import java.util.List;

public interface UserService {
    RegisteredUserResponse registerUser(RegisterUserRequest registerUserRequest);


    Product findProduct(String productName);

    List<Product> findProductsByCategoryName(String categoryName);

    Cart addProductToCart(List<AddToCartRequest> addToCartRequestList, Long userId);

    User findUserBy(Long userId);

    List<Cart> removeProductFromCartBy(String productName);

    ProductReviewResponse productReview(ProductReviewRequest productReviewRequest, Long userId, Cart cartResponse);
}
