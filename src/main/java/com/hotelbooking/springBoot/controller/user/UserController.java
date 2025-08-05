package com.hotelbooking.springBoot.controller.user;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hotelbooking.springBoot.dto.BookingDto;
import com.hotelbooking.springBoot.dto.RoomDto;
import com.hotelbooking.springBoot.dto.UserDto;
import com.hotelbooking.springBoot.service.booking.BookingInterface;
import com.hotelbooking.springBoot.service.room.RoomInterface;
import com.hotelbooking.springBoot.service.user.UserInterface;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UserInterface userInterface;
    private RoomInterface roomInterface;
    private BookingInterface bookingInterface;

// used
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDto> create(
            @RequestPart("userDto") String userDto,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) throws IOException

    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        UserDto userDto1 = mapper.readValue(userDto, UserDto.class);
        return new ResponseEntity<>(userInterface.create(userDto1,image), HttpStatus.CREATED);
    }

    @GetMapping("/{getUserByUserName}")
    public ResponseEntity<UserDto> getById(@PathVariable String getUserByUserName) {
        return new ResponseEntity<>(userInterface.getUserByUserName(getUserByUserName), HttpStatus.OK);
    }



    @GetMapping("/image/{getUserByEmail}")
    public ResponseEntity<Resource> getUserWithImageById(@PathVariable String getUserByEmail) throws MalformedURLException {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(userInterface.getUserWithImageById(getUserByEmail).userImageDto().getFileType()))
                .body(userInterface.getUserWithImageById(getUserByEmail).resource());

    }

//used
    @GetMapping("/rooms")
    public ResponseEntity<List<RoomDto>> getAllRoom(){
        return new ResponseEntity<>(roomInterface.getAll(),HttpStatus.OK);
    }

//    used
    @GetMapping("/{username}/booking")
    public ResponseEntity<List<BookingDto>> getBookingsByUser(@PathVariable String username){
        return new ResponseEntity<>(bookingInterface.getBookingByUser(username),HttpStatus.OK);
    }



}

