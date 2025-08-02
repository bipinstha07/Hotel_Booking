package com.hotelbooking.springBoot.service.booking;

import com.hotelbooking.springBoot.dto.BookingDto;
import com.hotelbooking.springBoot.dto.UpdateBookingDto;

import java.util.List;

public interface BookingInterface {
    BookingDto addBooking(BookingDto bookingDto);
    List<BookingDto> getAll();
    List<BookingDto> getAllByUser(String userId);
    void updateBookingStatus(Long bookingId,String bookingStatus);
    List<BookingDto> getBookingByUser(String username);
}
