package com.essies.ecommerce.security.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.essies.ecommerce.security.JwtConfig;
import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static java.time.ZoneOffset.UTC;

@Service
@AllArgsConstructor
public class EssiesEcommerceJwtService implements JwtService{
    private final JwtConfig jwtConfig;
    private final UserDetailsService userDetailsService;
    @Override
    public String generateTokenFor(String username) {
        return JWT.create()
                .withSubject("access_token")
                .withClaim("username", username)
                .withExpiresAt(LocalDateTime.now().plusDays(jwtConfig.getJwtDuration())
                        .toInstant(UTC))
                .sign(HMAC512(jwtConfig.getJwtSecretKey().getBytes()));
    }

    @Override
    public boolean validate(String token) {
        Algorithm algorithm = HMAC512(jwtConfig.getJwtSecretKey().getBytes());
        DecodedJWT decodedJWT = JWT.require(algorithm)
                .build().verify(token);
        return isValidToken(decodedJWT);
    }

    private boolean isValidToken(DecodedJWT decodedJWT) {
        return isTokenNotExpired(decodedJWT) && isTokenWithValidIssuer(decodedJWT);
    }

    private boolean isTokenWithValidIssuer(DecodedJWT decodedJWT) {
        return decodedJWT.getIssuer().equals("sync");
    }

    private boolean isTokenNotExpired(DecodedJWT decodedJWT) {
        Instant expiresAt = decodedJWT.getExpiresAtAsInstant();
        return expiresAt != null && Instant.now().isAfter(expiresAt);
    }

    @Override
    public UserDetails extractUserDetailsFrom(String token) throws UsernameNotFoundException {
        Algorithm algorithm = HMAC512(jwtConfig.getJwtSecretKey().getBytes());
        DecodedJWT decodedJWT = JWT.require(algorithm)
                .build()
                .verify(token);
        String username = decodedJWT.getClaim("username").as(String.class);
        return userDetailsService.loadUserByUsername(username);
    }
}
