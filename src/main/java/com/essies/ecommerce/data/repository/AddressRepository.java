package com.essies.ecommerce.data.repository;

import com.essies.ecommerce.data.model.Address;
import com.essies.ecommerce.data.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
