package com.hotelbooking.springBoot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hotelbooking.springBoot.entity.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    private String id;
    private String customerName;
    private String customerEmail;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Long phoneNumber;
    private LocalDateTime bookingCreated = LocalDateTime.now();
    private Integer totalPrice;
    private String bookingStatus;
    private String notes;
    private Integer numberOfGuest;
    private String roomId;
    private RoomDto roomEntity;
    private RoomType roomType;
    private String roomNumber;
    private String userId;
    private UserDto userDto;
    private String paymentStatus;
    private String paymentIntentId;


}
