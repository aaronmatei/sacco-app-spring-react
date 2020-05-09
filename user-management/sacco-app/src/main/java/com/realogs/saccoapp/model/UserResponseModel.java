package com.realogs.saccoapp.model;

import lombok.Data;

@Data
public class UserResponseModel {
    private Long userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;

    public UserResponseModel(Long userId, String firstName, String lastName, String username, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
    }
}
