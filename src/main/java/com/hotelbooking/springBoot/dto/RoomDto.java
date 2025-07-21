package com.hotelbooking.springBoot.dto;

import com.hotelbooking.springBoot.entity.Booking;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(
            regexp = "SINGLE_AC|SINGLE_DELUXE|SINGLE_NORMAL|DOUBLE_AC|DOUBLE_DELUXE|DOUBLE_NORMAL|FOUR_SEATER_AC|FOUR_SEATER_DELUXE|FOUR_SEATER_NORMAL",
            message = "Invalid room type"
    )
    private String roomType;
    private Integer pricePerNight;
    private Integer capacity;
//    private List<BookingDto> booking;
}
