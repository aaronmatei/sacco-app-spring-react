package com.realogs.saccoapp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "First Name cannot be null")
    @Size(min = 2,message = "First Name must be at least 2 characters long")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Last Name cannot be null")
    @Size(min = 2,message = "Last Name must be at least 2 characters long")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Username cannot be null")
    @Size(min = 2,message = "Username must be at least 2 characters long")
    @Column(name = "username", unique = true)
    private String username;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email not valid")
    @Column(name = "email", unique = true)
    private String email;

   // @ValidPassword
    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;


}
