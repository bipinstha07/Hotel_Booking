package com.hotelbooking.springBoot.service;

import com.hotelbooking.springBoot.dto.UserDto;
import com.hotelbooking.springBoot.dto.UserImageDto;
import com.hotelbooking.springBoot.dto.UserImageWithResource;
import com.hotelbooking.springBoot.entity.UserImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserImageInterface {
    UserImageDto upload(MultipartFile file, String userId) throws IOException;
    UserImageWithResource loadImage(Long userId);
}
