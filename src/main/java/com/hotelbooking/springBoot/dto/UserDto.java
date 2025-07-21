package com.hotelbooking.springBoot.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String name;

    @Email(message = "Invalid Email Format")
    private String email;

    @Size(min = 5, message = "Length must be greater than 5")
    private String password;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    private String number;
    private String address;
    private LocalDate dateOfBirth;
    private RoleDto role;
}
