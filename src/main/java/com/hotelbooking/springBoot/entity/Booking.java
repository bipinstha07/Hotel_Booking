package com.hotelbooking.springBoot.entity;

import jakarta.persistence.*;
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
    private String id;
    private String customerName;
    private String customerEmail;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Long phoneNumber;
    private LocalDateTime bookingCreated;
    private Integer totalPrice;
    private String bookingStatus;
    private String notes;
    private Integer numberOfGuest;
    private String paymentStatus;
    private String paymentIntentId;

    @ManyToOne
    private Room room;

    @ManyToOne
    private User user;


}
