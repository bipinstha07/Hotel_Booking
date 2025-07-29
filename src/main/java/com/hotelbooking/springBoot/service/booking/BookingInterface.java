package com.hotelbooking.springBoot.service.booking;

import com.hotelbooking.springBoot.dto.BookingDto;
import com.hotelbooking.springBoot.dto.UpdateBookingDto;

import java.util.List;

public interface BookingInterface {
    BookingDto addBooking(BookingDto bookingDto);
    List<BookingDto> getAll();
    void updateBookingStatus(Long bookingId,String bookingStatus);
}
