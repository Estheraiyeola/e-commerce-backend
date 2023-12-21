package com.essies.ecommerce.data.repository;

import com.essies.ecommerce.data.model.Product;
import com.essies.ecommerce.exception.ProductDoesNotExistException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
    @Modifying
    @Transactional
    @Query("DELETE FROM Product e WHERE e.name = :name")
    void deleteProductByName(String name);
}
