package com.hotelbooking.springBoot.dto;

import java.time.LocalDate;

public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String number;
    private String address;
    private LocalDate dateOfBirth;
    private RoleDto role;
}
