package com.hotelbooking.springBoot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {

    @Id
    private Long id;
    private String customerName;
    private String customerEmail;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDate bookingCreated;

    @ManyToOne
    private Room room;


}
