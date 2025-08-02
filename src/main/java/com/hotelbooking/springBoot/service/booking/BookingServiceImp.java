package com.hotelbooking.springBoot.service.booking;

import com.hotelbooking.springBoot.dto.BookingDto;
import com.hotelbooking.springBoot.dto.RoomDto;
import com.hotelbooking.springBoot.entity.Booking;
import com.hotelbooking.springBoot.entity.Room;
import com.hotelbooking.springBoot.entity.User;
import com.hotelbooking.springBoot.exceptionHandling.ResourceNotFoundException;
import com.hotelbooking.springBoot.exceptionHandling.TimeConflictException;
import com.hotelbooking.springBoot.repository.BookingRepo;
import com.hotelbooking.springBoot.repository.RoomRepo;
import com.hotelbooking.springBoot.repository.UserRepo;
import com.hotelbooking.springBoot.service.Mailing.SendEmailService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
public class BookingServiceImp implements BookingInterface {

    private  ModelMapper modelMapper;
    private BookingRepo bookingRepo;
    private RoomRepo roomRepo;
    private UserRepo userRepo;
    private SendEmailService sendEmailService;

    private transient final Logger logger = LoggerFactory.getLogger(BookingServiceImp.class);

    @Override
    public BookingDto addBooking(BookingDto bookingDto){
        Room room = roomRepo.findById(bookingDto.getRoomId()).orElseThrow(()-> new ResourceNotFoundException("No Room Found"));
        LocalDate localDate = LocalDate.now();
    if(bookingDto.getCheckInDate().isBefore(localDate) ||
            bookingDto.getCheckOutDate().isBefore(bookingDto.getCheckInDate()) ||
            bookingDto.getCheckOutDate().isBefore(localDate) ){
        logger.error("Time Conflict! in user Input");
        throw new TimeConflictException("Time Conflict! Sorry Cannot proceed ");
    }

        List<Booking> bookingByCheckInDateBetween =  bookingRepo.findBookingsBetweenDates(bookingDto.getRoomId(),bookingDto.getCheckInDate(),bookingDto.getCheckOutDate());
        if (!bookingByCheckInDateBetween.isEmpty()) {
            logger.error("Room is already Booked on chosen date");
            throw new TimeConflictException("Not available on this Date");
        }

        bookingDto.setBookingStatus("PENDING");
        logger.info(bookingDto.getRoomId());
        Booking booking = modelMapper.map(bookingDto,Booking.class);
        booking.setRoom(room);
        User user = userRepo.findByEmail(bookingDto.getCustomerEmail()).orElse(null);
        booking.setUser(user);
        Booking savedBooking = bookingRepo.save(booking);


            String body = String.format(
                    "Dear %s,\n\n" +
                            "Your request to book a %s room has been received.\n\n" +
                            "Check-in Date: %s\n" +
                            "Check-out Date: %s\n\n" +
                            "Status: Pending\n\n" +
                            "You will be notified within 24 hours regarding the approval.\n\n" +
                            "Thank you for choosing us.\n\n" +
                            "Best regards,\n" +
                            "Hotel Booking Team",
                    savedBooking.getCustomerName(),  // Optional, if name is available
                    room.getRoomType(),
                    savedBooking.getCheckInDate(),
                    savedBooking.getCheckOutDate()
            );

            String subject = "Room Booking Request Received: BookingId: "+ savedBooking.getId();


//            sendEmailService.sendEmail(savedBooking.getCustomerEmail(), body, subject);

        logger.info("Booking Proceed for Id: {} with Name {} and email {} ",savedBooking.getId(),savedBooking.getCustomerName(),savedBooking.getCustomerEmail());

        BookingDto bookingDto1 = modelMapper.map(savedBooking,BookingDto.class);
        bookingDto1.setRoomEntity(modelMapper.map(room, RoomDto.class));
        return bookingDto1;
    }

    @Override
    public List<BookingDto> getAll() {
      List<Booking> booking =  bookingRepo.findAll();

      List<BookingDto> bookingDtos = booking.stream().map((book)-> modelMapper.map(book,BookingDto.class)).toList();
        System.out.println(bookingDtos.get(1).getRoomNumber()+"Room Number Testing");
      logger.info("All Booking Requested");
      return bookingDtos;
    }

    @Override
    public List<BookingDto> getAllByUser(String userId) {
        return List.of();
    }

    @Override
    public void updateBookingStatus(Long roomId, String bookingStatus) throws ResourceNotFoundException {
       Booking booking = bookingRepo.findById(roomId).orElseThrow(()-> new ResourceNotFoundException("No Booking Found"));
       Room room = roomRepo.findById(booking.getRoom().getId()).orElseThrow(()-> new ResourceNotFoundException("No Room Found"));
       try {
           booking.setBookingStatus(bookingStatus);
           bookingRepo.save(booking);
           if(!Objects.equals(bookingStatus.toUpperCase(), "DELETED")) {
               String body = String.format(
                       "Dear %s,\n\n" +
                               "Your booking for a %s room has been updated.\n\n" +
                               "Check-in Date: %s\n" +
                               "Check-out Date: %s\n\n" +
                               "Updated Status: %s\n\n" +
                               "Thank you for booking with us.\n\n" +
                               "Best regards,\n" +
                               "Hotel Booking Team",
                       booking.getCustomerName(),
                       room.getRoomType(),
                       booking.getCheckInDate(),
                       booking.getCheckOutDate(),
                       booking.getBookingStatus()
               );

               String subject = "Room Booking Update for BookingId: " + booking.getId();

//               sendEmailService.sendEmail(booking.getCustomerEmail(), body, subject);
           }
           logger.info("Booking Status Changed for ID: {} to {}",booking.getId(),booking.getBookingStatus());
       }
       catch (Exception e){
           logger.error("Cannot Change the Status for ID: {} to {}",booking.getId(),booking.getBookingStatus());
           throw new ResourceNotFoundException("Cannot Perform Action");
       }


    }

    @Override
    public List<BookingDto> getBookingByUser(String username) {
        List<Booking> bookings = bookingRepo.findBookingsByUserEmail(username);
        return bookings.stream().map(book->modelMapper.map(book,BookingDto.class)).toList();
    }


}
