package com.hotelbooking.springBoot.controller.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hotelbooking.springBoot.config.security.JwtHelper;
import com.hotelbooking.springBoot.dto.ErrorResponseDto;
import com.hotelbooking.springBoot.dto.JwtResponse;
import com.hotelbooking.springBoot.dto.LoginRequest;
import com.hotelbooking.springBoot.dto.UserDto;
import com.hotelbooking.springBoot.service.user.UserInterface;
import com.hotelbooking.springBoot.service.user.UserServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtHelper jwtHelper;
    private UserServiceImp userServiceImp;
    private UserInterface userInterface;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest)
    {
        try{

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.username(),loginRequest.password());
            System.out.println(authenticationToken.getCredentials());
            this.authenticationManager.authenticate(authenticationToken);
            System.out.println(loginRequest);
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.username());
            String token = this.jwtHelper.generateToken(userDetails);
            JwtResponse jwtResponse = new JwtResponse(token,userDetails.getUsername());
            return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
        }
        catch (Exception ex){
            ErrorResponseDto er = new ErrorResponseDto("Invalid Credentials",401,false);
            return new ResponseEntity<>(er,HttpStatus.BAD_REQUEST);
        }
    }



    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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

}
