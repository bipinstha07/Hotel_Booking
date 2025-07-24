package com.hotelbooking.springBoot.controller.user;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hotelbooking.springBoot.dto.RoomDto;
import com.hotelbooking.springBoot.dto.UserDto;
import com.hotelbooking.springBoot.service.RoomInterface;
import com.hotelbooking.springBoot.service.UserInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UserInterface userInterface;
    private RoomInterface roomInterface;


    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDto> create(
            @RequestPart("userDto") String userDto,
            @RequestPart("image") MultipartFile image
    ) throws IOException

    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        UserDto userDto1 = mapper.readValue(userDto, UserDto.class);
        return new ResponseEntity<>(userInterface.create(userDto1,image), HttpStatus.CREATED);
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<RoomDto>> getAllRoom(){
        return new ResponseEntity<>(roomInterface.getAll(),HttpStatus.OK);
    }

}

