package com.hotelbooking.springBoot.controller.user;


import com.hotelbooking.springBoot.dto.UserDto;
import com.hotelbooking.springBoot.service.UserInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UserInterface userInterface;

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(userInterface.create(userDto), HttpStatus.CREATED);
    }

}
