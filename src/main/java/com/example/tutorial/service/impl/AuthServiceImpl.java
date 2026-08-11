package com.example.tutorial.service.impl;

import com.example.tutorial.dto.Request.LoginRequest;
import com.example.tutorial.dto.Response.LoginResponse;
import com.example.tutorial.entity.User;
import com.example.tutorial.exception.ResourceNotFoundException;
import com.example.tutorial.exception.UnauthorizedException;
import com.example.tutorial.repository.UserRepository;
import com.example.tutorial.service.AuthService;
import com.example.tutorial.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findUserByEmail(request.getEmail()).orElseThrow(() ->
                new UnauthorizedException("Invalid Email")
        );
        boolean passwordMatch = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );
        if (!passwordMatch) {
            throw new UnauthorizedException("Invalid password");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(
                user.getId(),
                user.getName(),
                user.getEmail(), token);
    }
}
