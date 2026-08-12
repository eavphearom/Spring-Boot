package com.example.tutorial.repository;

import com.example.tutorial.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/*JpaRepository is already have method such as findAll,findById,save,delete,...*/
public interface UserRepository extends BaseRepository<User, Long> {
    Page<User> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String name,
            String email,
            Pageable pageable
    );

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);

    /*""" = Java text block, used for multi-line strings.*/
    @Query("""
                Select u from User u
                join u.role r
                where r.id = :roleId
            """)
    List<User> findUserByRoleId(@Param("roleId") Long roleId);
}
