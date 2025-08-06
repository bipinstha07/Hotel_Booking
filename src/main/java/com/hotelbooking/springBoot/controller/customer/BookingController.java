package com.hotelbooking.springBoot.controller.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hotelbooking.springBoot.dto.BookingDto;
import com.hotelbooking.springBoot.service.booking.BookingInterface;
import com.stripe.exception.StripeException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/user/booking")
@AllArgsConstructor
public class BookingController {

    private BookingInterface bookingInterface;

    @PostMapping("/create")
    public ResponseEntity<Map<String,String>> createBooking(@RequestBody String bookingJson) throws JsonProcessingException, StripeException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        BookingDto bookingDto = objectMapper.readValue(bookingJson, BookingDto.class);
        System.out.println(bookingDto.getRoomId());
        Map<String,String> res = bookingInterface.addBooking(bookingDto);
        return  new ResponseEntity<>(res, HttpStatus.CREATED);
    }



    @PostMapping("/confirm")
    public ResponseEntity<String> confirmBooking(@RequestBody Map<String, String> data) {
       return  new ResponseEntity<>(bookingInterface.getPaymentBooking(data),HttpStatus.OK);
    }

}
