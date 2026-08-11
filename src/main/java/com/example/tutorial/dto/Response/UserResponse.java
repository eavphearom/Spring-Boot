package com.example.tutorial.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String profile;
    private String profile_url;
    private Long role_id;


//    public UserResponse(Long id, String name, String email,Long roleId) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.roleId = roleId;
//    }
//
//    public Long getId() {
//        return id;
//    }
//    public Long getRoleId() {
//        return roleId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
}