package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.Cart;
import com.essies.ecommerce.data.model.Order;
import com.essies.ecommerce.data.model.User;

public interface OrderService {

    Order createOrder(Cart cart, User user);

    Order saveOrder(Order order);

    void deleteAll();
}
