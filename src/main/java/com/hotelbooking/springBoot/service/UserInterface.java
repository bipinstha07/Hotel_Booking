package com.hotelbooking.springBoot.service;

import com.hotelbooking.springBoot.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserInterface {
    UserDto create(UserDto userDto, MultipartFile file) throws IOException;
    UserDto getUserById(String userId);
}
