package com.hotelbooking.springBoot.service;

import com.hotelbooking.springBoot.dto.UserDto;
import com.hotelbooking.springBoot.dto.UserImageWithResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface UserInterface {
    UserDto create(UserDto userDto, MultipartFile file) throws IOException;
    UserDto getUserById(String userId);
    UserImageWithResource getUserWithImageById(String userId) throws MalformedURLException;
}
