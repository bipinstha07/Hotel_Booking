package com.hotelbooking.springBoot.service;

import com.hotelbooking.springBoot.dto.BookingDto;
import com.hotelbooking.springBoot.entity.Booking;
import com.hotelbooking.springBoot.repository.BookingRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingServiceImp implements BookingInterface{

    private  ModelMapper modelMapper;
    private BookingRepo bookingRepo;

@Override
public BookingDto addBooking(BookingDto bookingDto){
        Booking booking = modelMapper.map(bookingDto,Booking.class);
        Booking savedBooking = bookingRepo.save(booking);
        return modelMapper.map(savedBooking,BookingDto.class);
    }

}
