package com.essies.ecommerce.data.repository;

import com.essies.ecommerce.data.model.Cart;
import com.essies.ecommerce.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
