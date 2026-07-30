package com.example.tutorial.controller;

import com.example.tutorial.entity.User;
import com.example.tutorial.response.ApiResponse;
import com.example.tutorial.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ApiResponse<List<User>> getUser() {/*List is Collection, User → the type of objects stored in that list*/
        List<User> users = userService.getAllUsers();
        return new ApiResponse<>(
                false,
                "OK",
                "Success",
                users
        );
    }

    @PostMapping
    public ApiResponse<User> createUser(@Valid @RequestBody User user) {/*first User return Type ,  second User parameter Type, user para variable*/
        User createdUser = userService.createUser(user);

        return new ApiResponse<>(
                false,
                "OK",
                "User created successfully",
                createdUser
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ApiResponse<>(
                false,
                "OK",
                "Success",
                user
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<User> UpdateById(@PathVariable Long id, @Valid @RequestBody User user) {
        User updateUser = userService.updateUser(id, user);

        return new ApiResponse<>(
                false,
                "OK",
                "User updated successfully",
                updateUser
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Object> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ApiResponse<>(
                false,
                "OK",
                "User deleted successfully",
                null
        );
    }
}
