package com.bankcase.auth_service.dto;

import com.bankcase.auth_service.model.enums.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
}

