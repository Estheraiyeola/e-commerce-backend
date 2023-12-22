package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.Cart;
import com.essies.ecommerce.data.model.Order;
import com.essies.ecommerce.data.model.User;
import com.essies.ecommerce.dto.response.ProcessOrderResponse;

import java.util.List;

public interface OrderService {

    Order createOrder(Cart cart, User user);

    Order saveOrder(Order order);

    void deleteAll();

    Order findOrder(Long orderId);

    List<Order> checkListOfOrders();

    ProcessOrderResponse processOrder(Long orderId);
}
