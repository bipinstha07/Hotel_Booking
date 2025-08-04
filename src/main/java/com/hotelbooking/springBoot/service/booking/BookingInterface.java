package com.hotelbooking.springBoot.service.booking;

import com.hotelbooking.springBoot.dto.BookingDto;
import com.stripe.exception.StripeException;

import java.util.List;
import java.util.Map;

public interface BookingInterface {
    Map<String,String> addBooking(BookingDto bookingDto) throws StripeException;
    List<BookingDto> getAll();
    List<BookingDto> getAllByUser(String userId);
    void updateBookingStatus(String bookingId,String bookingStatus);
    List<BookingDto> getBookingByUser(String username);
    String getPaymentBooking( Map<String, String> data);
}
