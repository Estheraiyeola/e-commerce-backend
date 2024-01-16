package com.essies.ecommerce.data.repository;

import com.essies.ecommerce.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long userId);
    Optional<User> findByUsername(String username);
}
