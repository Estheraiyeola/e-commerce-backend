package com.essies.ecommerce.data.repository;

import com.essies.ecommerce.data.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
