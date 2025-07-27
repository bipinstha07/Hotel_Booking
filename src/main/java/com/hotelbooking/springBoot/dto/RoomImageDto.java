package com.hotelbooking.springBoot.dto;

import com.hotelbooking.springBoot.entity.Room;
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
public class RoomImageDto {
    private String id;
    private String fileName;
    private String fileType;
    private Long size;
    private LocalDateTime uploadedTime = LocalDateTime.now();

    private Room room;

}
