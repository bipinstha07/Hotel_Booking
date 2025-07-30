package com.hotelbooking.springBoot.repository;

import com.hotelbooking.springBoot.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepo extends JpaRepository<Booking,Long> {

    @Query("SELECT booking FROM Booking booking WHERE booking.room.id = :roomId AND (booking.checkInDate BETWEEN :checkIn AND :checkOut OR booking.checkOutDate BETWEEN :checkIn AND :checkOut)")
    List<Booking> findBookingsBetweenDates(@Param("roomId") String roomId, @Param("checkIn") LocalDate checkIn, @Param("checkOut") LocalDate checkOut);

    @Query("SELECT booking FROM Booking booking WHERE booking.user.id=:userId")
    List<Booking> findBookingsByUserId(@Param("userId") String userId);

}
