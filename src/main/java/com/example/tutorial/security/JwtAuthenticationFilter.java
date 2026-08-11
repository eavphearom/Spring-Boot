package com.example.tutorial.security;

import com.example.tutorial.entity.User;
import com.example.tutorial.exception.ResourceNotFoundException;
import com.example.tutorial.repository.UserRepository;
import com.example.tutorial.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
/*OncePerRequestFilter: It allows our JWT filter to run once for every HTTP request*/
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authHeader.substring(7);
        String email = jwtService.extractEmail(token);
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User Not Found")
                );
        if (jwtService.isTokenValid(token, user)) {
//            log.debug("========== JWT DEBUG ==========");
//            log.debug("EMAIL: " + email);
//            log.debug("ROLE: " + user.getRole().getName());
//            log.debug("TOKEN VALID: " + jwtService.isTokenValid(token, user));
//            log.debug("===============================");
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            null,
                            List.of(
                                    new SimpleGrantedAuthority(
                                            "ROLE_" + user.getRole().getName()
                                    )
                            )
                    );

            SecurityContextHolder.getContext().setAuthentication(authentication);
//            log.debug("AUTHORITIES: " + authentication.getAuthorities());
//            log.debug("AUTHENTICATED: " + authentication.isAuthenticated());
//
//            System.out.println("EMAIL: " + email);
//            System.out.println("TOKEN VALID: " + jwtService.isTokenValid(token, user));

        }
        filterChain.doFilter(request, response);
    }

}
