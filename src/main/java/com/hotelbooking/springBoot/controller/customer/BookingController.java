package com.hotelbooking.springBoot.controller.customer;

import com.hotelbooking.springBoot.dto.BookingDto;
import com.hotelbooking.springBoot.service.BookingInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
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


    @GetMapping()
    public ResponseEntity<List<BookingDto>> getAll(){
        return new ResponseEntity<>(bookingInterface.getAll(),HttpStatus.OK);
    }
}
