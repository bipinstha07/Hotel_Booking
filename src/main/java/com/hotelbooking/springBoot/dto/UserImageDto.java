package com.hotelbooking.springBoot.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class UserImageDto {
    @Id
    private String Id;
    private String fileName;
    private String fileType;
    private Long size;
    private LocalDateTime uploadedTime = LocalDateTime.now();

    private String user;

}
