package com.example.tutorial.entity;

import com.example.tutorial.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity /*tells JPA: this Java class represents a database table*/
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")/*tells JPA the ID should be generated automatically.*/
    @SequenceGenerator(
            name = "user_seq",
            sequenceName = "USER_SEQ",
            allocationSize = 1
    )/*configures which Oracle sequence JPA should use to generate that ID*/
    private Long id;

    //    @NotBlank(message = "Name is required")
//    @Size(max=100, message = "Name cannot exceed 100 characters")
    @Column(name = "name", nullable = true, length = 100) /*configures the database column*/
    private String name;

    //    @NotBlank(message = "Email is required")
//  @Email(message = "Email is invalid")
    @Column(name = "email", nullable = true, unique = true)
    private String email;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }


//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }

    @Column
    private String password;
    private String phone;
    private String profile;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private UserStatus status;

}
