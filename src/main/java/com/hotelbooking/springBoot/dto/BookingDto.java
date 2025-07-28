package com.hotelbooking.springBoot.dto;

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
public class BookingDto {

    private Long id;
    private String customerName;
    private String customerEmail;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime bookingCreated = LocalDateTime.now();
    private Integer totalPrice;
    private String bookingStatus;
    private String notes;

    private RoomDto room;

}
