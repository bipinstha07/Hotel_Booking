package com.hotelbooking.springBoot.controller.admin;

import com.hotelbooking.springBoot.dto.BookingDto;
import com.hotelbooking.springBoot.dto.RoomDto;
import com.hotelbooking.springBoot.service.BookingServiceImp;
import com.hotelbooking.springBoot.service.RoomInterface;
import com.hotelbooking.springBoot.service.RoomServiceImp;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/room")
@AllArgsConstructor
public class RoomController {

    private RoomInterface roomInterface;

    @PostMapping("/create")
    public ResponseEntity<RoomDto> createBooking(@Valid  @RequestBody RoomDto roomDto){
       RoomDto roomDto1 = roomInterface.add(roomDto);
        return  new ResponseEntity<>(roomDto1, HttpStatus.CREATED);
    }
}
