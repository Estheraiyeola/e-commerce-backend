package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.Cart;
import com.essies.ecommerce.dto.request.AddToCartRequest;

import java.util.List;

public interface CartService {
    Cart addToCart(List<AddToCartRequest> addToCartRequestList, Long userId);

    void deleteAll();

    List<Cart> removeProductFromCart(String productName);
}
