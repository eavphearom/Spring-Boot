package com.example.tutorial.service;

import com.example.tutorial.common.filter.BaseFilter;
import com.example.tutorial.dto.Request.UserRequest;
import com.example.tutorial.dto.Response.UserResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserResponse> getAllUsers();

    Page<UserResponse> getUserPagination(BaseFilter filter);

    UserResponse createUser(UserRequest request);

    UserResponse getUserById(Long id);

    UserResponse getUserByEmail(String email);

    List<Map<String, Object>> getUserByRoleId(Long roleId);

    UserResponse updateUser(Long id, UserRequest request);

    void deleteUser(Long id);
}
