package com.hotelbooking.springBoot.service;

import com.hotelbooking.springBoot.dto.BookingDto;

import java.util.List;

public interface BookingInterface {
    BookingDto addBooking(BookingDto bookingDto);
    List<BookingDto> getAll();
}
