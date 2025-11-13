package com.bankcase.auth_service;

import com.bankcase.auth_service.dto.LoginRequest;
import com.bankcase.auth_service.dto.RegisterRequest;
import com.bankcase.auth_service.dto.TokenResponse;
import com.bankcase.auth_service.model.User;
import com.bankcase.auth_service.service.JwtService;
import com.bankcase.auth_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return userService.register(request.getUsername(), request.getPassword(), request.getRole());
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        User user = userService.authenticate(request.getUsername(), request.getPassword());
        String token = jwtService.generateToken(user.getUsername(), user.getRole().name());
        return new TokenResponse(token);
    }
}

