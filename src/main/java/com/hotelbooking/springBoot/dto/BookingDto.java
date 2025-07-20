package com.hotelbooking.springBoot.dto;

import com.hotelbooking.springBoot.entity.Room;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
    private LocalDate bookingCreated;
    private Room room;

}
