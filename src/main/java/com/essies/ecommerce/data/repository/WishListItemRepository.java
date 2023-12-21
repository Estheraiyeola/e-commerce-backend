package com.essies.ecommerce.data.repository;

import com.essies.ecommerce.data.model.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListItemRepository extends JpaRepository<WishListItem, Long> {
}
