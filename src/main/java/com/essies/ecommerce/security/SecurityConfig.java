package com.essies.ecommerce.security;

import com.essies.ecommerce.security.filters.EssiesEcommerceAuthenticationFilter;
import com.essies.ecommerce.security.filters.JwtAuthorizationFilter;
import com.essies.ecommerce.security.manager.EssiesEcommerceAuthenticationManager;
import com.essies.ecommerce.security.services.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static org.springframework.http.HttpMethod.POST;


@Configuration
@AllArgsConstructor
public class SecurityConfig{
    private final EssiesEcommerceAuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        var authenticationFilter = new EssiesEcommerceAuthenticationFilter(authenticationManager, jwtService);
        authenticationFilter.setFilterProcessesUrl("/api/login");
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .cors(c -> c
                        .configurationSource(request -> {
                            var cors = new CorsConfiguration();
                            cors.setAllowedOrigins(List.of("http://localhost:8080"));
                            cors.setAllowedMethods(List.of("GET", "POST", "DELETE", "PATCH", "OPTIONS"));
                            cors.setAllowedHeaders(List.of("*"));
                            cors.setAllowCredentials(true);
                            return cors;
                        })
                )
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthorizationFilter(jwtService), authenticationFilter.getClass())
                .authorizeHttpRequests(c -> c.requestMatchers(POST, "/api/admin/register", "/api/user/register", "/api/login")
                        .permitAll()
                        .anyRequest().authenticated())
                .build();
    }
}
