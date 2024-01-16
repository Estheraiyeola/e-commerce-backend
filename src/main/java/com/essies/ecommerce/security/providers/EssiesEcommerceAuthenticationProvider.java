package com.essies.ecommerce.security.providers;

import com.essies.ecommerce.security.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EssiesEcommerceAuthenticationProvider implements AuthenticationProvider {
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);


        boolean isValidPasswordMatch = passwordEncoder.matches(password, userDetails.getPassword());
        if (isValidPasswordMatch){
            Authentication authenticationResult = new UsernamePasswordAuthenticationToken(username, password,userDetails.getAuthorities());
            return authenticationResult;
        }
        throw new BadCredentialsException("Incorrect credentials, username or password incorrect");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
