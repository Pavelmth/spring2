package com.geekbrains.septembermarket.utils;


import com.geekbrains.septembermarket.utils.validation.FieldMatch;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
public class SystemUser {
    @NotNull(message = "not null check")
    @Size(min = 3, message = "username length must be greater than 2 symbols")
    private String username;

    @NotNull(message = "is required")
    @Size(min = 4, message = "password is too short")
    private String password;

    @NotNull(message = "is required")
    @Size(min = 4, message = "password is too short")
    private String matchingPassword;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String firstName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName;

    @NotNull(message = "is required")
    @Size(min = 4, message = "is required")
    @Email
    private String email;

    @NotNull(message = "is required")
    @Size(min = 8, message = "is required")
    private String phone;
}
