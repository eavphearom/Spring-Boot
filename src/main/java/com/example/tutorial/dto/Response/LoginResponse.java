package com.example.tutorial.dto.Response;

import lombok.*;

@Data
@AllArgsConstructor
public class LoginResponse {
    private Long id;
    private String name;
    private String email;
    private String token;
}
