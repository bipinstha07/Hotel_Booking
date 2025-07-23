package com.hotelbooking.springBoot.controller.user;


import com.hotelbooking.springBoot.dto.UserDto;
import com.hotelbooking.springBoot.service.UserInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UserInterface userInterface;

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto, @RequestParam("image") MultipartFile image) throws IOException {
        return new ResponseEntity<>(userInterface.create(userDto,image), HttpStatus.CREATED);
    }

}
