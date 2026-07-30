package com.example.tutorial.repository;

import com.example.tutorial.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
/*JpaRepository is already have method such as findAll,findById,save,delete,...*/
public interface UserRepository extends JpaRepository<User,Long> {
}
