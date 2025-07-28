package com.hotelbooking.springBoot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;
    private String name;

    @Column(unique = true)
    private String email;

    private String password;
    private String number;
    private String address;
    private LocalDate dateOfBirth;
    private LocalDateTime accountCreation;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private UserImage userImage;

}
