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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/getAll")
    public ResponseEntity<List<RoomDto>> getAll(){
        return new ResponseEntity<>(roomInterface.getAll(),HttpStatus.OK);
    }

    @PutMapping("/update/{roomId}")
    public ResponseEntity<RoomDto> updateById(@PathVariable String roomId,@RequestBody RoomDto roomDto){
        return new ResponseEntity<>(roomInterface.updateById(roomId,roomDto),HttpStatus.CREATED);
    }


}
