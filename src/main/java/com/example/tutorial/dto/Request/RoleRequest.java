package com.example.tutorial.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*We use a DTO to control what data comes into or goes out of our API, instead of exposing the Entity directly.*/
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest {
    @NotBlank(message = "Role is required")
    @Size(max = 100, message = "Role cannot exceed 100 characters")
    private String name;


//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
}
