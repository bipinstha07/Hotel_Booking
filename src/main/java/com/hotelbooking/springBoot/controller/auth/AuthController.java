package com.hotelbooking.springBoot.controller.auth;

import com.hotelbooking.springBoot.config.security.JwtHelper;
import com.hotelbooking.springBoot.dto.ErrorResponseDto;
import com.hotelbooking.springBoot.dto.JwtResponse;
import com.hotelbooking.springBoot.dto.LoginRequest;
import com.hotelbooking.springBoot.service.user.UserServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtHelper jwtHelper;
    private UserServiceImp userServiceImp;

    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest)
    {
        try{
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.username(),loginRequest.password());
            this.authenticationManager.authenticate(authenticationToken);

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

}
