package com.hotelbooking.springBoot.controller.customer;

import com.hotelbooking.springBoot.dto.BookingDto;
import com.hotelbooking.springBoot.service.BookingInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/booking")
@AllArgsConstructor
public class BookingController {

    private BookingInterface bookingInterface;

    @PostMapping("/create")
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto bookingDto){
       BookingDto savedBookingDto = bookingInterface.addBooking(bookingDto);
        return  new ResponseEntity<>(savedBookingDto, HttpStatus.CREATED);
    }
}
