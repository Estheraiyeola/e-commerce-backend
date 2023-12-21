package com.essies.ecommerce.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisteredUserResponse {
    private String username;
    private String password;
    private String status;
    private Long userId;
}
