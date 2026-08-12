package com.example.tutorial.util;

import com.example.tutorial.domain.AuthUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtil {

    private SecurityUtil() {
    }

    public static AuthUser getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated()) {
            return null;
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof AuthUser authUser) {
            return authUser;
        }

        return null;
    }

    public static Long getCurrentUserId() {

        AuthUser authUser = getCurrentUser();

        return authUser != null
                ? authUser.getId()
                : null;
    }
}