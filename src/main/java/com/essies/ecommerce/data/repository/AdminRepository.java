package com.essies.ecommerce.data.repository;

import com.essies.ecommerce.data.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);

    Optional<Admin> findByUsername(String username);
}
