package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.OrderItem;
import com.essies.ecommerce.data.repository.OrderItemRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService{
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private EntityManager entityManager;

    @Override
    public void deleteAll() {
        orderItemRepository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteByProductId(Long id) {
        orderItemRepository.removeOrderItemByProductId(id);
    }



}
