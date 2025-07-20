package com.hotelbooking.springBoot.dto;

import com.hotelbooking.springBoot.entity.Booking;
import com.hotelbooking.springBoot.entity.RoomType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {

    private Long id;
    private RoomTypeDto roomType;
    private Integer pricePerNight;
    private Integer capacity;
    private List<Booking> booking;
}
