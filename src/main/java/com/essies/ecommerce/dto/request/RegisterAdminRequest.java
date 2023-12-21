package com.essies.ecommerce.dto.request;

import com.essies.ecommerce.data.model.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterAdminRequest {
    private String username;
    private String password;
    private String email;
    private Role role;
}
