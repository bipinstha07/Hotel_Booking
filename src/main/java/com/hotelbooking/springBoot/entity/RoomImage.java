package com.hotelbooking.springBoot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RoomImage {
    @Id
    private String id;
    private String fileName;
    private String fileType;
    private Long size;
    private LocalDateTime uploadedTime = LocalDateTime.now();

    @ManyToOne
    private Room room;

}
