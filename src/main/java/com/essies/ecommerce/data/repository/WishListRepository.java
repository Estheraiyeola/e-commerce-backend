package com.essies.ecommerce.data.repository;

import com.essies.ecommerce.data.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList, Long> {
}
