package com.example.tutorial.controller;

import com.example.tutorial.dto.Request.LoginRequest;
import com.example.tutorial.dto.Response.LoginResponse;
import com.example.tutorial.response.ApiResponse;
import com.example.tutorial.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse data = authService.login(request);
        return ApiResponse.success(data);
    }
}