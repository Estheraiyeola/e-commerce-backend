package com.essies.ecommerce.dto.response;

import com.essies.ecommerce.data.model.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterAdminResponse {
    private String username;
    private String password;
    private String email;
    private String status;
    private Role role;
}
