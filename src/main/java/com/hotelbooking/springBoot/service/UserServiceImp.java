package com.hotelbooking.springBoot.service;

import com.hotelbooking.springBoot.dto.*;
import com.hotelbooking.springBoot.entity.User;
import com.hotelbooking.springBoot.entity.UserImage;
import com.hotelbooking.springBoot.exceptionHandling.ResourceNotFoundException;
import com.hotelbooking.springBoot.repository.UserRepo;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserInterface{

    private UserRepo userRepo;
    private ModelMapper modelMapper;
    private UserImageInterface userImageInterface;

    @Override
    public UserDto create(UserDto userDto, MultipartFile image) throws IOException {
        userDto.setRole(RoleDto.CUSTOMER);
        userDto.setId(UUID.randomUUID().toString());

        UserImage userImage= userImageInterface.upload(image,userDto.getId());

        User user = modelMapper.map(userDto,User.class);
        user.setUserImage(userImage);
        userImage.setUser(user);
        User savedUser = userRepo.save(user);
        UserDto savedUserDto = modelMapper.map(savedUser,UserDto.class);
        savedUserDto.setUserImage(userImage.getId());
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepo.findUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("No User Found");
        }
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserImageWithResource getUserWithImageById(String userId) throws MalformedURLException {

        User user = userRepo.findUserById(userId);
        if (user == null) throw new ResourceNotFoundException("User not found");
        UserImage userImage = user.getUserImage();

        Path path;

        if(!userImage.isActualImage()){
            path = Path.of("uploads/userImage/1745900042295.jpeg");
        }

        else{
            path = Paths.get(userImage.getFileName());
        }


        if(!Files.exists(path)){
            throw new ResourceNotFoundException("No Path found for image");
        }

        UrlResource urlResource =  new UrlResource(path.toUri());
        return new UserImageWithResource(modelMapper.map(userImage,UserImageDto.class), urlResource);


        }


}
