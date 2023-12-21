package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.OrderItem;

public interface OrderItemService {
    void deleteAll();


    void deleteByProductId(Long id);
}
