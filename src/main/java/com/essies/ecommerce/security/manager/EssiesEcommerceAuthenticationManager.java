package com.essies.ecommerce.security.manager;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EssiesEcommerceAuthenticationManager implements AuthenticationManager {
    private final AuthenticationProvider authenticationProvider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Class<?> authenticationClass = authentication.getClass();
        if (authenticationProvider.supports(authenticationClass)){
            return authenticationProvider.authenticate(authentication);
        }
        throw new ProviderNotFoundException("Failed to authenticate request");
    }
}
