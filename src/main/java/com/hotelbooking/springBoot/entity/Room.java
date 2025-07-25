package com.hotelbooking.springBoot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Room {

    @Id
    private String id;


    private RoomType roomType;

    private Integer pricePerNight;
    private Integer capacity;

    @OneToMany(mappedBy = "room")
    private List<Booking> booking;


}
