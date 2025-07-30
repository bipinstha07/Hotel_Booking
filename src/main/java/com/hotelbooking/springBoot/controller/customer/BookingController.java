package com.hotelbooking.springBoot.controller.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hotelbooking.springBoot.dto.BookingDto;
import com.hotelbooking.springBoot.service.booking.BookingInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user/booking")
@AllArgsConstructor
public class BookingController {

    private BookingInterface bookingInterface;

    @PostMapping("/create")
    public ResponseEntity<BookingDto> createBooking(@RequestBody String bookingJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        BookingDto bookingDto = objectMapper.readValue(bookingJson, BookingDto.class);
        System.out.println(bookingDto.getRoomId());
        BookingDto savedBookingDto = bookingInterface.addBooking(bookingDto);
        return  new ResponseEntity<>(savedBookingDto, HttpStatus.CREATED);
    }


    @GetMapping("/{userId}/getAll")
    public ResponseEntity<List<BookingDto>> getAll(@PathVariable String userId){
        return new ResponseEntity<>(bookingInterface.getAllByUser(userId),HttpStatus.OK);
    }
}
