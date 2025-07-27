package com.hotelbooking.springBoot.repository;

import com.hotelbooking.springBoot.entity.RoomImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomImageRepo extends JpaRepository<RoomImage,String> {
}
