package com.essies.ecommerce.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistory extends JpaRepository<OrderHistory, String> {
}
