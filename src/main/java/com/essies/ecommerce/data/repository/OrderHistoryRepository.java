package com.essies.ecommerce.data.repository;

import com.essies.ecommerce.data.model.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, String> {
}
