package com.essies.ecommerce.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class JwtConfig {
    @Getter
    @Value("${jwt.signing.key}")
    private String jwtSecretKey;
    @Value("${jwt.expiry.days}")
    private String jwtDuration;

    public int getJwtDuration(){
        return Integer.parseInt(jwtDuration);
    }
}
