package com.hotelbooking.springBoot.controller.admin;

import com.hotelbooking.springBoot.dto.BookingDto;
import com.hotelbooking.springBoot.dto.RoomDto;
import com.hotelbooking.springBoot.service.BookingServiceImp;
import com.hotelbooking.springBoot.service.RoomServiceImp;
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

    private RoomServiceImp roomServiceImp;

    @PostMapping("/create")
    public ResponseEntity<RoomDto> createBooking(@RequestBody RoomDto roomDto){
       RoomDto roomDto1 = roomServiceImp.add(roomDto);
        return  new ResponseEntity<>(roomDto1, HttpStatus.CREATED);
    }
}
