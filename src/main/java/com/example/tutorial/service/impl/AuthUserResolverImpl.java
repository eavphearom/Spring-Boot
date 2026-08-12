package com.example.tutorial.service.impl;

import com.example.tutorial.domain.AuthUser;
import com.example.tutorial.entity.User;
import com.example.tutorial.repository.UserRepository;
import com.example.tutorial.service.AuthUserResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserResolverImpl implements AuthUserResolver {

    private final UserRepository userRepository;

    @Override
    public AuthUser resolve() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated()) {
            return null;
        }

        String email = authentication.getName();

        User user = userRepository.findUserByEmail(email)
                .orElse(null);

        if (user == null) {
            return null;
        }

        return new AuthUser(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole() != null
                        ? user.getRole().getId()
                        : null
        );
    }
}