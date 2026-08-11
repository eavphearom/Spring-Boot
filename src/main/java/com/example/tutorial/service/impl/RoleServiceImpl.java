package com.example.tutorial.service.impl;

import com.example.tutorial.dto.Request.RoleRequest;
import com.example.tutorial.dto.Response.RoleResponse;
import com.example.tutorial.entity.Role;
import com.example.tutorial.repository.RoleRepository;
import com.example.tutorial.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

//    public RoleServiceImpl(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }

    @Override
    public List<RoleResponse> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(role -> new RoleResponse(
                role.getId(),
                role.getName()
        )).toList();
    }

    @Override
    public RoleResponse createRole(RoleRequest request) {
        Role role = new Role();
        role.setName(request.getName());

        Role save = roleRepository.save(role);

        return new RoleResponse(
                save.getId(),
                save.getName()
        );
    }
}
