package com.essies.ecommerce.security.filters;

import com.essies.ecommerce.dto.request.LoginRequest;
import com.essies.ecommerce.dto.response.LoginResponse;
import com.essies.ecommerce.security.manager.EssiesEcommerceAuthenticationManager;
import com.essies.ecommerce.security.services.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static com.essies.ecommerce.util.AppUtils.ACCESS_TOKEN_FIELD_VALUE;

@RequiredArgsConstructor
public class EssiesEcommerceAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final EssiesEcommerceAuthenticationManager authenticationManager;
    private ObjectMapper mapper = new ObjectMapper();
    private final JwtService jwtService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try(InputStream stream = request.getInputStream()) {
            LoginRequest loginRequest = mapper.readValue(stream, LoginRequest.class);
            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();

            Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authenticatedResult = authenticationManager.authenticate(authentication);

            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authenticatedResult);
            return authenticatedResult;
        }catch (IOException exception){
            throw new BadCredentialsException("Authentication Failed");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        LoginResponse loginResponse = new LoginResponse();
        String token = jwtService.generateTokenFor(authResult.getPrincipal().toString());
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getOutputStream().write(mapper.writeValueAsBytes(Map.of(ACCESS_TOKEN_FIELD_VALUE, token)));
        response.flushBuffer();
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getOutputStream().flush();


    }
}
