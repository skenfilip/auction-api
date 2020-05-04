package com.finki.students.auctionapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @Email(message = "Username needs to be an email")
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Please enter your full name")
    private String fullName;
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must contain at least 8 characters")
    private String password;
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    @NotBlank(message = "Home address is required")
    private String homeAddress;
}
