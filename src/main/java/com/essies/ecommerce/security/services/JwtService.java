package com.essies.ecommerce.security.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateTokenFor(String username);
    boolean validate(String token);
    UserDetails extractUserDetailsFrom(String token);
}
