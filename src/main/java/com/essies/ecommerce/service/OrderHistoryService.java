package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.Order;
import com.essies.ecommerce.data.model.OrderHistory;

import java.util.List;

public interface OrderHistoryService {
    OrderHistory saveOrderHistory(Order order, Long userId);

    void deleteAll();

    List<OrderHistory> findAll();
}
