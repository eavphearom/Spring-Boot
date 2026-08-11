package com.example.tutorial.service;

import com.example.tutorial.dto.Request.LoginRequest;
import com.example.tutorial.dto.Response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
