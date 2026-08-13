package com.example.tutorial.service.impl;

import com.example.tutorial.common.filter.BaseFilter;
import com.example.tutorial.domain.AuthUser;
import com.example.tutorial.dto.Request.UserRequest;
import com.example.tutorial.dto.Response.UserResponse;
import com.example.tutorial.entity.Role;
import com.example.tutorial.entity.User;
import com.example.tutorial.enums.ImageDirectory;
import com.example.tutorial.exception.ResourceNotFoundException;
import com.example.tutorial.repository.RoleRepository;
import com.example.tutorial.repository.UserRepository;
import com.example.tutorial.service.MediaService;
import com.example.tutorial.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final MediaService mediaService;

//    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public Page<UserResponse> getUserPagination(BaseFilter filter) {
        /* Create pagination settings*/
        Pageable pageable = PageRequest.of(filter.getPageNo() - 1, filter.getPerPage(), Sort.by("id").descending());
        /* Prepare variable to hold database result*/
        Page<User> users;
        /*Get users from database*/
        if (filter.getSearch() != null && !filter.getSearch().isBlank()) {

            users = userRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(filter.getSearch(), filter.getSearch(), pageable);

        } else {
            users = userRepository.findAll(pageable);
        }
        /*Convert User → DTO*/
        return users.map(this::mapToResponse);


    }

    @Override
    public UserResponse createUser(UserRequest request) {

        Role role = roleRepository.findById(request.getRole_id())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role not found")
                );

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setRole(role);
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        // Upload profile
        if (request.getProfile() != null &&
                !request.getProfile().isEmpty()) {
            String profile = mediaService.uploadImage(
                    request.getProfile(),
                    ImageDirectory.PROFILE
            );
            user.setProfile(profile);
        }

        User data = userRepository.save(user);

        return mapToResponse(data);
    }

    @Override
//    public UserResponse getUserById(Long id) {
//
//        User user = userRepository.findById(id).get();
//
//        return new UserResponse(
//                user.getId(),
//                user.getName(),
//                user.getEmail(),
//                user.getPhone(),
//                user.getRole() != null ? user.getRole().getId() : null
//        );
//    }
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User Not Found")
                );

        return mapToResponse(user);
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        return mapToResponse(user);
    }

    @Override
    public List<Map<String, Object>> getUserByRoleId(Long roleId) {

        List<User> users = userRepository.findUserByRoleId(roleId);

        return users.stream()
                .map(user -> Map.<String, Object>of(
                        "id", user.getId(),
                        "name", user.getName(),
                        "email", user.getEmail(),
                        "role_name", user.getRole().getName()
                ))
                .toList();
    }

    /*   ↓ stream()
        process one by one
           ↓ map()
        User → UserResponse
           ↓ toList()
        List<UserResponse>
     */

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        Role role = roleRepository.findById(request.getRole_id()).orElseThrow(() -> new ResourceNotFoundException("Role Not Found"));
        existingUser.setName((request.getName()));
        existingUser.setEmail(request.getEmail());
        existingUser.setPhone(request.getPhone());
        existingUser.setRole(role);
        // Update profile only when a new image is provided
        if (request.getProfile() != null &&
                !request.getProfile().isEmpty()) {

            String newProfile = mediaService.uploadImage(
                    request.getProfile(),
                    ImageDirectory.PROFILE
            );

            if (existingUser.getProfile() != null &&
                    !existingUser.getProfile().isBlank()) {
                mediaService.deleteImage(existingUser.getProfile());
            }

            existingUser.setProfile(newProfile);
        }
        User user = userRepository.save(existingUser);
        return mapToResponse(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        return userRepository.softDeleteById(id);
    }

    private UserResponse mapToResponse(User user) {

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getProfile(),
                mediaService.getImageUrl(user.getProfile()),
                user.getRole() != null ? user.getRole().getId() : null
        );
    }

}
