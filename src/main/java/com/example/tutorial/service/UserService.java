package com.example.tutorial.service;

import com.example.tutorial.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User createUser (User user);
    User getUserById(Long id);
    User updateUser(Long id,User user);
    void deleteUser(Long id);
}
