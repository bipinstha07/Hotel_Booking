package com.hotelbooking.springBoot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookingDto {
    private Long bookingId;
    private String bookingStatus;

}
