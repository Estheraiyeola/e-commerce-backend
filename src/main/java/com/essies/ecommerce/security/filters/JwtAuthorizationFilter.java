package com.essies.ecommerce.security.filters;

import com.essies.ecommerce.security.services.JwtService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import static com.essies.ecommerce.util.AppUtils.JWT_PREFIX;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@AllArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        boolean isRequestToPublicEndpoint = getPublicEndpoints().contains(request.getServletPath());
        if (isRequestToPublicEndpoint) filterChain.doFilter(request, response);
        else authorizeRequest(request, response, filterChain);

    }

    private void authorizeRequest(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(AUTHORIZATION);
        boolean isRequestWithAuthorizationHeader = authHeader != null;
        if (isRequestWithAuthorizationHeader) authorize(authHeader);
        filterChain.doFilter(request, response);
    }

    private void authorize(String authHeader) {
        String token = extractTokenFrom(authHeader);
        boolean isValidToken = jwtService.validate(token);
        if (isValidToken) addUserCredentialsToContext(token);
    }

    private void addUserCredentialsToContext(String token) {
        UserDetails userDetails = jwtService.extractUserDetailsFrom(token);
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), "", userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String extractTokenFrom(String authHeader) {
        return authHeader.substring(JWT_PREFIX.length());
    }

    private Set<String> getPublicEndpoints() {
        return Set.of("/login");
    }
}
