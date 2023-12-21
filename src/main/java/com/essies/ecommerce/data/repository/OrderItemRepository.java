package com.essies.ecommerce.data.repository;

import com.essies.ecommerce.data.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    void removeOrderItemByProductId(Long productId);
    OrderItem findOrderItemById(Long id);
}
