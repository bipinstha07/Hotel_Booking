package com.hotelbooking.springBoot.service;

import com.hotelbooking.springBoot.dto.UserDto;
import com.hotelbooking.springBoot.dto.UserImageDto;
import com.hotelbooking.springBoot.dto.UserImageWithResource;
import com.hotelbooking.springBoot.entity.User;
import com.hotelbooking.springBoot.entity.UserImage;
import com.hotelbooking.springBoot.repository.UserImageRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class UserImageImp implements UserImageInterface{

    @Value("{userImage.file.path}")
    private String imagePath;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserImageRepo userImageRepo;



    @Override
    public UserImageDto upload(MultipartFile file, String userId) throws IOException {

        if(!Files.exists(Paths.get(imagePath))){
            Files.createDirectories(Paths.get(imagePath));
        }

        String fullFileName = imagePath+ file.getName();
        Files.copy(file.getInputStream(),Paths.get(fullFileName), StandardCopyOption.REPLACE_EXISTING);
        UserImageDto userImageDto = new UserImageDto();
        userImageDto.setId(UUID.randomUUID().toString());
        userImageDto.setFileName(fullFileName);
        userImageDto.setFileType(file.getContentType());
        userImageDto.setSize(file.getSize());
        userImageDto.setUser(userId);

        UserImage userImage = modelMapper.map(userImageDto,UserImage.class);
        userImageRepo.save(userImage);
        return userImageDto;
    }

    @Override
    public UserImageWithResource loadImage(Long userId) {
        return null;
    }
}
