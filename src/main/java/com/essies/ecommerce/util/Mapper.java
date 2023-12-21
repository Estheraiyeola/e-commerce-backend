package com.essies.ecommerce.util;

import com.essies.ecommerce.data.model.Admin;
import com.essies.ecommerce.data.model.Role;
import com.essies.ecommerce.dto.request.RegisterAdminRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Mapper {
    public static Admin map(RegisterAdminRequest registerAdminRequest) {
        Admin admin = new Admin();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        admin.setUsername(registerAdminRequest.getUsername());
        admin.setEmail(registerAdminRequest.getEmail());
        String encodedPassword = passwordEncoder.encode(registerAdminRequest.getPassword());
        admin.setPassword(encodedPassword);
        admin.setRole(Role.ADMIN);
        return admin;
    }
}
