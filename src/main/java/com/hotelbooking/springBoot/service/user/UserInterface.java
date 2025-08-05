package com.hotelbooking.springBoot.service.user;

import com.hotelbooking.springBoot.dto.UserDto;
import com.hotelbooking.springBoot.dto.UserImageWithResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface UserInterface {
    UserDto create(UserDto userDto, MultipartFile file) throws IOException;

    UserDto getUserByUserName(String userName);

    UserImageWithResource getUserWithImageById(String userId) throws MalformedURLException;

    UserDto updateUserById(String userName, UserDto userDto, MultipartFile file) throws IOException;
}
