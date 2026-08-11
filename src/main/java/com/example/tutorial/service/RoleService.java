package com.example.tutorial.service;
import com.example.tutorial.dto.Request.RoleRequest;
import com.example.tutorial.dto.Response.RoleResponse;
import java.util.List;

public interface RoleService {
    List<RoleResponse> getAllRoles();
    RoleResponse createRole(RoleRequest request);
}
