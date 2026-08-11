package com.example.tutorial.dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
/*We use a DTO to control what data comes into or goes out of our API, instead of exposing the Entity directly.*/

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name canot exceed 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;

    @NotNull(message = "Role is required")
    @JsonProperty("role_id")
    private Long role_id;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Phone is required")
    private String phone;

    private MultipartFile profile;

    /*
    example for create is require and update is optional
    * @NotBlank(
        message = "Name is required",
        groups = Create.class
    )
    private String name;
* */

//    public Long getRoleId() {return roleId;}
//    public void setRoleId(Long roleId) {this.roleId = roleId;}
//
//    public String getName() {return name;}
//    public void setName(String name) {this.name = name;}
//
//    public String getEmail() {
//        return email;
//    }
//    public void setEmail(String email) {
//        this.email = email;
//    }
}
