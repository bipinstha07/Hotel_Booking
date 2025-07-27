package com.hotelbooking.springBoot.service.booking;

import com.hotelbooking.springBoot.dto.BookingDto;
import com.hotelbooking.springBoot.dto.RoomDto;
import com.hotelbooking.springBoot.entity.Booking;
import com.hotelbooking.springBoot.entity.Room;
import com.hotelbooking.springBoot.exceptionHandling.ResourceNotFoundException;
import com.hotelbooking.springBoot.exceptionHandling.TimeConflictException;
import com.hotelbooking.springBoot.repository.BookingRepo;
import com.hotelbooking.springBoot.repository.RoomRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingServiceImp implements BookingInterface {

    private  ModelMapper modelMapper;
    private BookingRepo bookingRepo;
    private RoomRepo roomRepo;

@Override
public BookingDto addBooking(BookingDto bookingDto){
        Room room = roomRepo.findById(bookingDto.getRoom().getId()).orElseThrow(()-> new ResourceNotFoundException("No Room Found"));
    LocalDate localDate = LocalDate.now();
    if(bookingDto.getCheckInDate().isBefore(localDate) ||
            bookingDto.getCheckOutDate().isBefore(bookingDto.getCheckInDate()) ||
            bookingDto.getCheckOutDate().isBefore(localDate) ){

        throw new TimeConflictException("Time Conflict! Sorry Cannot proceed ");
    }
        Booking booking = modelMapper.map(bookingDto,Booking.class);
        Booking savedBooking = bookingRepo.save(booking);
        BookingDto bookingDto1 = modelMapper.map(savedBooking,BookingDto.class);
        bookingDto1.setRoom(modelMapper.map(room, RoomDto.class));
        return bookingDto1;
    }

    @Override
    public List<BookingDto> getAll() {
      List<Booking> booking =  bookingRepo.findAll();
      List<BookingDto> bookingDtos = booking.stream().map((book)->{
         return modelMapper.map(book,BookingDto.class);
      }).toList();

      return bookingDtos;
    }


}
