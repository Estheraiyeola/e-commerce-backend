package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.*;
import com.essies.ecommerce.data.repository.OrderRepository;
import com.essies.ecommerce.dto.response.ProcessOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public Order findOrder(Long orderId) {
        return orderRepository.findOrderById(orderId);
    }

    @Override
    public List<Order> checkListOfOrders() {
        return orderRepository.findAll();
    }

    @Override
    public ProcessOrderResponse processOrder(Long orderId) {
        Order order = orderRepository.findOrderById(orderId);
        order.setStatus(OrderStatus.COMPLETED_ADMIN);

        ProcessOrderResponse orderResponse = new ProcessOrderResponse();
        orderResponse.setMessage("Order would be with user soon");
        orderResponse.setDateProcessed(LocalDateTime.now());
        return orderResponse;
    }
}
