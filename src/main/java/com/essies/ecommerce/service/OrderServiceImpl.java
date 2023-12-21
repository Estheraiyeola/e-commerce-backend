package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.*;
import com.essies.ecommerce.data.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Order createOrder(Cart cart, User user) {
        Order order = new Order();
        order.setOrderDate(cart.getDateCreated());
        order.setStatus(OrderStatus.PENDING_USER);
        order.setBillingAddressId(user.getAddress().getId());
        order.setTotalAmount(cart.getTotal());
        order.setShippingAddressId(user.getAddress().getId());
        order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        return order;
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteAll() {
        orderRepository.deleteAll();
    }
}
