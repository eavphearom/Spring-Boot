package com.example.tutorial.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthUser {

    private Long id;
    private String name;
    private String email;
    private Long roleId;
}