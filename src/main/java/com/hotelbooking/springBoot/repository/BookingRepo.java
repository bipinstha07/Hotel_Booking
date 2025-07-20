package com.hotelbooking.springBoot.repository;

import com.hotelbooking.springBoot.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking,Long> {
}
