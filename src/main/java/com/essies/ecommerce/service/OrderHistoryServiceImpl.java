package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.Order;
import com.essies.ecommerce.data.model.OrderHistory;
import com.essies.ecommerce.data.repository.OrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService{
    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Override
    public OrderHistory saveOrderHistory(Order order, Long userId) {
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrderId(order.getId());
        orderHistory.setOrderList(List.of(order));
        orderHistory.setUserId(userId);
        orderHistoryRepository.save(orderHistory);
        return orderHistory;
    }

    @Override
    public void deleteAll() {
        orderHistoryRepository.deleteAll();
    }

    @Override
    public List<OrderHistory> findAll() {
        return orderHistoryRepository.findAll();
    }
}
