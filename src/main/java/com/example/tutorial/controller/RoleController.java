package com.example.tutorial.controller;

import com.example.tutorial.dto.Response.RoleResponse;
import com.example.tutorial.dto.Request.RoleRequest;
import com.example.tutorial.response.ApiResponse;
import com.example.tutorial.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private final RoleService roleService;

//    public RoleController(RoleService roleService) {
//        this.roleService = roleService;
//    }

    @GetMapping
    public ApiResponse<List<RoleResponse>> getRole() {/*List is Collection, User → the type of objects stored in that list*/
        return ApiResponse.success(roleService.getAllRoles());
    }

    @PostMapping
    public ApiResponse<RoleResponse> createUser(@Valid @RequestBody RoleRequest request) {/*first User return Type ,  second User parameter Type, user para variable*/
        RoleResponse data = roleService.createRole(request);

        return new ApiResponse<>(
                false,
                "OK",
                "Role created successfully",
                data
        );
    }
}
