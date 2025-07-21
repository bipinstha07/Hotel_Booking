package com.hotelbooking.springBoot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {

    private Long id;
    private RoomTypeDto roomType;
    private Integer pricePerNight;
    private Integer capacity;
//    private List<Booking> booking;
}
