package com.essies.ecommerce.security;

import com.essies.ecommerce.data.model.Admin;
import com.essies.ecommerce.data.model.Role;
import com.essies.ecommerce.data.model.User;
import com.essies.ecommerce.service.AdminService;
import com.essies.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    private final AdminService adminService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username)
                .orElse(null);


        if (user != null){
            return buildUserDetails(user.getUsername(), user.getPassword(), Collections.singleton(user.getRole()));
        }
        Admin admin = adminService.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with username: " + username));
        System.out.println(admin.getUsername());

        return buildUserDetails(admin.getUsername(), admin.getPassword(), Collections.singleton(admin.getRole()));
    }

    private UserDetails buildUserDetails(String username, String password, Collection<Role> roles) {
        return new  org.springframework.security.core.userdetails.User(
                username,
                password,
                mapRolesToAuthorities(roles)
        );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE" + role.name()))
                .collect(Collectors.toList());
    }
}
