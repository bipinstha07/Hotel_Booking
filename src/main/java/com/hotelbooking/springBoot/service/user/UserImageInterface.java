package com.hotelbooking.springBoot.service.user;

import com.hotelbooking.springBoot.dto.UserImageWithResource;
import com.hotelbooking.springBoot.entity.UserImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserImageInterface {
    UserImage upload(MultipartFile file, String userId) throws IOException;
    UserImageWithResource loadImage(Long userId);
}
