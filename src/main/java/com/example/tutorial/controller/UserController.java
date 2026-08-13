package com.example.tutorial.controller;

import com.example.tutorial.common.filter.BaseFilter;
import com.example.tutorial.dto.Request.UserRequest;
import com.example.tutorial.dto.Response.UserResponse;
import com.example.tutorial.response.ApiResponse;
import com.example.tutorial.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getUser() {/*List is Collection, User → the type of objects stored in that list*/
        /*users.stream() mean start processing the users one by one*/
//        List<UserResponse> users = userService.getAllUsers();
        return ApiResponse.success(userService.getAllUsers());
    }

    @GetMapping("/pagination")
    public ApiResponse<List<UserResponse>> getUserPagination(BaseFilter filter) {
        return ApiResponse.paginate(userService.getUserPagination(filter), filter.getPageNo());
    }

    @PostMapping
    public ApiResponse<UserResponse> createUser(@Valid @ModelAttribute  UserRequest request) {/*first User return Type ,  second User parameter Type, user para variable*/
        UserResponse user = userService.createUser(request);
        return ApiResponse.success(user);
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse user = userService.getUserById(id);
        return ApiResponse.success(user);
    }

    @GetMapping("/email/{email}")
    public ApiResponse<UserResponse> getUserByEmail(@PathVariable String email) {
        UserResponse user = userService.getUserByEmail(email);
        return ApiResponse.success(user);
    }

    @GetMapping("/role/{roleId}")
    public ApiResponse<List<Map<String, Object>>> getUserByRoleId(@PathVariable Long roleId) {
        return ApiResponse.success(userService.getUserByRoleId(roleId));
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> UpdateById(@PathVariable Long id, @Valid @ModelAttribute  UserRequest request) {
        UserResponse user = userService.updateUser(id, request);
        return ApiResponse.success(user);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Boolean> deleteUser(@PathVariable Long id) {
        return ApiResponse.success(
                userService.deleteUser(id)
        );
    }
}
