package com.hotelbooking.springBoot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomDto {

    private String id;
    @Pattern(
            regexp = "SINGLE_AC|SINGLE_DELUXE|SINGLE_NORMAL|DOUBLE_AC|DOUBLE_DELUXE|DOUBLE_NORMAL|FOUR_SEATER_AC|FOUR_SEATER_DELUXE|FOUR_SEATER_NORMAL",
            message = "Invalid room type"
    )
    private String roomType;
    private String roomNumber;
    private Integer pricePerNight;
    private Integer capacity;
    private String description;
    private String amenities;
    private List<String> roomImageIds;
//    private List<BookingDto> booking;
}
